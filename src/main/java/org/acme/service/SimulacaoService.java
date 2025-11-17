package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.SimulacaoRequestDTO;
import org.acme.dto.SimularInvestimentoResponseDto;
import org.acme.entity.ProdutoValidadoEntity;
import org.acme.entity.SimulacaoEntity;
import org.acme.repository.ProdutoValidadoRepository;
import org.acme.repository.SimulacaoRepository;

import java.util.List;


@ApplicationScoped
public class SimulacaoService {


    @Inject
    ProdutoValidadoRepository produtosRepository;

    @Inject
    SimulacaoRepository simulacaoRepository;


    public SimularInvestimentoResponseDto simularInvestimento(SimulacaoRequestDTO request){
        List<ProdutoValidadoEntity> produtos = produtosRepository.buscarPorTipo(request.getTipoProduto());

        //TODO:Criar motor de recomendação e mesmo que vazio sempre fara uma indicação de um produto compativel com o cliente
        ProdutoValidadoEntity produto = produtos.stream().findFirst().get();
        double rentabilidadeEfetiva = Math.pow((1 + produto.getRentabilidade()), ( ((double)request.getPrazo() /12))) - 1;
        SimulacaoEntity simulacao = new SimulacaoEntity(request,produto,rentabilidadeEfetiva);
        simulacaoRepository.persist(simulacao);
        return new SimularInvestimentoResponseDto(produto,simulacao);
    }





}
