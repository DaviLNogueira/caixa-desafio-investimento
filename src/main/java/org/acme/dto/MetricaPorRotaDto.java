package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class MetricaPorRotaDto {
    private String nome;
    private Long quantidadeChamadas;
    private Double mediaTempoRespostaMs;

}