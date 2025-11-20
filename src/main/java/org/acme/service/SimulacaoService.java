package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProdutoDiaResponseDto;
import org.acme.dto.SimilacaoResponseDto;
import org.acme.dto.SimulacaoRequestDTO;
import org.acme.dto.SimularInvestimentoResponseDto;
import org.acme.entity.*;
import org.acme.exceptions.ClienteNaoEncontrado;
import org.acme.exceptions.ProdutoNaoEncontradoException;
import org.acme.repository.InvestimentoClienteRepository;
import org.acme.repository.PerfilRiscoRepository;
import org.acme.repository.ProdutoValidadoRepository;
import org.acme.repository.SimulacaoRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;


@ApplicationScoped
public class SimulacaoService {


    @Inject
    ProdutoValidadoRepository produtosRepository;

    @Inject
    SimulacaoRepository simulacaoRepository;

    @Inject
    InvestimentoClienteRepository investimentoClienteRepository;

    @Inject
    PerfilRiscoRepository perfilRiscoRepository;

    private PerfilEntity alocarPerfilPontuacao(Double pontuacao) {
        if (pontuacao <= 35) {
            return PerfilEntity.CONSERVADOR;
        } else if (pontuacao <= 70) {
            return PerfilEntity.MODERADO;
        }
        return PerfilEntity.AGRESSIVO;
    }

    private Integer calcularPontuacaoLiquidesMovimentacao(Double mediaPrazoMeses) {
        if (mediaPrazoMeses <= 24) {
            return 20;
        } else if (mediaPrazoMeses <= 60) {
            return 50;
        }
        return 100;
    }


    private Integer calcularPontuacaoFrequenciaMovimentacao(Long movimentacoes) {
        if (movimentacoes == 1) {
            return 10;
        } else if (movimentacoes <= 3) {
            return 20;
        } else if (movimentacoes <= 6) {
            return 70;
        }
        return 100;

    }

    private Integer calcularPontuacaoVolumeMovimentacao(Double valorInvestido) {
        if (valorInvestido < 5000) {
            return 10;
        } else if (valorInvestido <= 20000) {
            return 30;
        } else if (valorInvestido <= 50000) {
            return 60;
        } else if (valorInvestido <= 100000) {
            return 80;
        }
        return 100;
    }

    private PerfilRiscoEntity calcularPerfilRisco(List<InvestimentoClienteEntity> investimentosCliente, SimulacaoRequestDTO request) {
        Double volume = investimentosCliente.stream().mapToDouble(InvestimentoClienteEntity::getValorInvestido).sum();

        LocalDate dataAtual = LocalDate.now();
        LocalDate ultimoTrimestre = dataAtual.minusMonths(3);

        Instant instant = ultimoTrimestre.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date dataUltimoTrimestre = Date.from(instant);
        Long frequenciaNoTrimestre = investimentosCliente.stream().
                filter(p -> p.getData().after(dataUltimoTrimestre)).count();

        OptionalDouble mediaLiquedezMeses = investimentosCliente.stream().mapToDouble(InvestimentoClienteEntity::getPrazoMeses).average();

        double pontuacao = calcularPontuacaoVolumeMovimentacao(volume) * 0.40
                + calcularPontuacaoFrequenciaMovimentacao(frequenciaNoTrimestre) * 0.40
                + calcularPontuacaoLiquidesMovimentacao(mediaLiquedezMeses.orElse(0.0)) * 0.20;
        PerfilEntity perfil = alocarPerfilPontuacao(pontuacao);
        return new PerfilRiscoEntity(request.getClienteId(), perfil, (int) Math.floor(pontuacao));
    }

    private static int getPrioridadeRisco(RiscoEntity risco, PerfilEntity perfil) {

        return switch (perfil) {
            case CONSERVADOR -> switch (risco) {
                case BAIXO -> 1;
                default -> 99;
            };

            case MODERADO -> switch (risco) {
                case MODERADO -> 1;
                case BAIXO -> 2;
                default -> 99;
            };

            case AGRESSIVO -> switch (risco) {
                case ALTO -> 1;
                case MODERADO -> 2;
                case BAIXO -> 3;
            };
        };
    }

    public static List<ProdutoValidadoEntity> filtrarEOrdenar(
            List<ProdutoValidadoEntity> produtos,
            PerfilEntity perfil
    ) {
        return produtos.stream()
                .filter(p -> filtrarPorPerfil(p, perfil))
                .sorted((p1, p2) -> ordenarPorPerfil(p1, p2, perfil))
                .toList();
    }

    private static int ordenarPorPerfil(ProdutoValidadoEntity p1, ProdutoValidadoEntity p2, PerfilEntity perfil) {
        int prioridade1 = getPrioridadeRisco(p1.getRisco(), perfil);
        int prioridade2 = getPrioridadeRisco(p2.getRisco(), perfil);
        if (prioridade1 != prioridade2) {
            return Integer.compare(prioridade1, prioridade2);
        }
        return Double.compare(p2.getRentabilidade(), p1.getRentabilidade());
    }


    private static boolean filtrarPorPerfil(ProdutoValidadoEntity p, PerfilEntity perfil) {
        return switch (perfil) {
            case CONSERVADOR -> p.getRisco() == RiscoEntity.BAIXO;
            case MODERADO -> p.getRisco() == RiscoEntity.MODERADO || p.getRisco() == RiscoEntity.BAIXO;
            case AGRESSIVO -> true;
        };
    }


    public SimularInvestimentoResponseDto simularInvestimento(SimulacaoRequestDTO request) {

        List<InvestimentoClienteEntity> investimentos =
                investimentoClienteRepository.findAllInvestimentoByClienteId(request.getClienteId());

        PerfilRiscoEntity perfil = calcularPerfilRisco(investimentos, request);
        PerfilRiscoEntity perfilExistente;
        try{
             perfilExistente = perfilRiscoRepository.findByClienteId(request.getClienteId());
            perfilExistente.atualizarPontuacao(
                    perfil.getPontuacao(),
                    perfil.getPerfil()
            );
        }catch (ClienteNaoEncontrado e ){
             perfilExistente = perfil;
        }

        perfilRiscoRepository.persist(perfilExistente);
        List<ProdutoValidadoEntity> produtos =
                produtosRepository.buscarPorTipo(request.getTipoProduto());

        List<ProdutoValidadoEntity> produtosAptos =
                filtrarEOrdenar(produtos, perfil.getPerfil());

        if(produtosAptos.isEmpty()){
            produtosAptos = filtrarEOrdenar(produtosRepository.buscarPorRisco(perfil.getPerfil()),perfil.getPerfil());
        }

        ProdutoValidadoEntity produtoSelecionado = produtosAptos.stream()
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException(perfil.getPerfil()));

        double rentabilidadeEfetiva = Math.pow((1 + produtoSelecionado.getRentabilidade()), (((double) request.getPrazo() / 12))) - 1;

        SimulacaoEntity simulacao = new SimulacaoEntity(request, produtoSelecionado, rentabilidadeEfetiva);
        InvestimentoClienteEntity investimento = new InvestimentoClienteEntity(simulacao);
        simulacaoRepository.persist(simulacao);
        investimentoClienteRepository.persist(investimento);
        return new SimularInvestimentoResponseDto(produtoSelecionado, simulacao);
    }


    public List<SimilacaoResponseDto> todasSimulacoes() {
        return simulacaoRepository.findAll().stream().map(
                SimilacaoResponseDto::new
        ).toList();
    }

    public List<ProdutoDiaResponseDto> simulacaoProdutoDia(String data) {
        LocalDate dataBusca;
        if (data == null) {
            dataBusca = LocalDate.now();
        } else {
            try {
                dataBusca = LocalDate.parse(data);
            } catch (Exception e) {
                dataBusca = LocalDate.now();
            }
        }
        return simulacaoRepository.agruparProdutoDia(dataBusca);
    }


}
