package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ResultadoSimulacaoDto;
import org.acme.dto.SimulacaoRequestDTO;
import org.acme.dto.SimularInvestimentoResponseDto;
import org.acme.entity.ProdutoValidadoEntity;
import org.acme.repository.ProdutoValidadoRepository;

import java.util.List;


@ApplicationScoped
public class SimulacaoService {


    @Inject
    private ProdutoValidadoRepository produtosRepository;


    public SimularInvestimentoResponseDto simularInvestimento(SimulacaoRequestDTO request){
        List<ProdutoValidadoEntity> produtos = produtosRepository.buscarPorTipo(request.getTipoProduto());

        //TODO:Criar motor de recomendação
        ProdutoValidadoEntity produto = produtos.stream().findFirst().get();
        double rentabilidadeEfetiva = Math.pow((1 + produto.getRentabilidade()), ( ((double)request.getPrazo() /12))) - 1;
        Double valorFinal = (request.getValor() * (1 + rentabilidadeEfetiva));

        ResultadoSimulacaoDto resultadoSimulacaoDto = new ResultadoSimulacaoDto(
                Math.round(valorFinal * 100) /100.0 ,rentabilidadeEfetiva, request.getPrazo()
        );

        return new SimularInvestimentoResponseDto(produto,resultadoSimulacaoDto);
    }





}
