package org.acme.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoRequestDTO {


    @NotNull(message = "O campo clienteId não deve estar nulo")
    private Long clienteId;

    @NotNull(message = "O campo valor não deve estar nulo")
    @Positive
    private Double valor;

    @NotNull
    @Positive
    private Integer prazo;

    @NotNull
    @NotEmpty
    private String tipoProduto;


}
