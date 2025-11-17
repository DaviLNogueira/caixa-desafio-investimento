package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDiaResponseDto {

    private String produto;
    private String data;
    private Long quantidadeSimulacoes;
    private Double mediaValorFinal;

}
