package org.acme.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.ProdutoValidadoEntity;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
@Jacksonized

public class SimularInvestimentoResponseDto {

    private ProdutoValidadoDto produtoValidado;

    private String dataSimulacao;

    private ResultadoSimulacaoDto resultadoSimulacao;

    public SimularInvestimentoResponseDto(ProdutoValidadoEntity produto, ResultadoSimulacaoDto resultadoSimulacao){
        this.produtoValidado = new ProdutoValidadoDto(produto);
        this.resultadoSimulacao = resultadoSimulacao;
    }


}
