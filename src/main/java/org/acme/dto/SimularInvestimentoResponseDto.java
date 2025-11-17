package org.acme.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.ProdutoValidadoEntity;
import org.acme.entity.SimulacaoEntity;
@Data
@Jacksonized

public class SimularInvestimentoResponseDto {

    private ProdutoValidadoDto produtoValidado;

    private String dataSimulacao;

    private ResultadoSimulacaoDto resultadoSimulacao;

    public SimularInvestimentoResponseDto(ProdutoValidadoEntity produto, SimulacaoEntity simulacao){
        this.produtoValidado = new ProdutoValidadoDto(produto);
        this.resultadoSimulacao = new ResultadoSimulacaoDto(simulacao);
        this.dataSimulacao = simulacao.getDataSimulacaoFormatada();
    }


}
