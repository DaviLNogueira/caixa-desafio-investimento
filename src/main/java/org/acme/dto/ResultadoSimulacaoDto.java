package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
public class ResultadoSimulacaoDto {

    private Double valorFinal;

    private Double rentabilidadeEfetiva;

    private Integer prazo;



}
