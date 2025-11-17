package org.acme.dto;


import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.SimulacaoEntity;

@Data
@Jacksonized
public class SimilacaoResponseDto {

    public SimilacaoResponseDto(SimulacaoEntity simulacao) {
        this.id = simulacao.getId();
        this.clinedId = simulacao.getIdCliente();
        this.produto = simulacao.getProduto().getNome();
        this.valorInvestido = simulacao.getValorInvestido();
        this.valorFinal = simulacao.getValorFinal();
        this.prazoMeses = simulacao.getPrazoMeses();
        this.dataSimulacao = simulacao.getDataSimulacaoFormatadaFromBanco();
    }


    private Integer id;

    private Integer clinedId;

    private String produto;

    private Double valorInvestido;

    private Double valorFinal;

    private Integer prazoMeses;

    private String dataSimulacao;
}
