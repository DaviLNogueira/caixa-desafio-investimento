package org.acme.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "investimento_cliente")
@Getter
@NoArgsConstructor
public class InvestimentoClienteEntity {

    public InvestimentoClienteEntity(SimulacaoEntity simulacao){
        this.clienteId = simulacao.getIdCliente();
        this.produtoValidado = simulacao.getProduto();
        this.rentabilidadeEfetiva = simulacao.getRentabilidadeEfetiva();
        this.valorInvestido = simulacao.getValorInvestido();
        this.prazoMeses = simulacao.getPrazoMeses();
        this.data = simulacao.getDataSimulacao();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @ManyToOne
    @JoinColumn(name = "produto_valido_id")
    private ProdutoValidadoEntity produtoValidado;

    private Double valorInvestido;

    private double rentabilidadeEfetiva;

    private Integer prazoMeses;

    private Date data;
}
