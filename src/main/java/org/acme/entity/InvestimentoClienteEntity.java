package org.acme.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "investimento_cliente")
@Getter
public class InvestimentoClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer clienteId;

    @ManyToOne
    @JoinColumn(name = "produto_valido_id")
    private ProdutoValidadoEntity produtoValidado;

    private Double valorInvestido;

    private double rentabilidadeEfetiva;

    private Integer prazoMeses;

    private Date data;
}
