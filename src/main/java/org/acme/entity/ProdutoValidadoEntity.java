package org.acme.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "produto_validado")
@Getter
public class ProdutoValidadoEntity {

    @Id
    private Integer id;

    private String nome;

    private String tipo;

    private Double rentabilidade;

    @Enumerated(EnumType.STRING)
    private RiscoEntity risco;

}
