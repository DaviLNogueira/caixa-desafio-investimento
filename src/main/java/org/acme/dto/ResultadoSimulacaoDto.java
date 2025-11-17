package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.SimulacaoEntity;

@Data
@Jacksonized
@AllArgsConstructor
public class ResultadoSimulacaoDto {

    public ResultadoSimulacaoDto(SimulacaoEntity simulacao){
        this.valorFinal = simulacao.getValorFinal();
        this.rentabilidadeEfetiva = simulacao.getRentabilidadeEfetiva();
        this.prazo = simulacao.getPrazoMeses();
    }

    private Double valorFinal;

    private Double rentabilidadeEfetiva;

    private Integer prazo;

}
