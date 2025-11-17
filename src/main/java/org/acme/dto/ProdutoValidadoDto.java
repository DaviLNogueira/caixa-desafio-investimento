package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.ProdutoValidadoEntity;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoValidadoDto {

    private Integer id;

    private String nome;

    private String tipo;

    private Double rentabilidade;

    private String risco;

    public ProdutoValidadoDto(ProdutoValidadoEntity produto) {
        this.id = produto.getId();
        this.risco = produto.getRisco().name();
        this.rentabilidade = produto.getRentabilidade();
        this.tipo = produto.getTipo();
        this.nome = produto.getNome();
    }
}
