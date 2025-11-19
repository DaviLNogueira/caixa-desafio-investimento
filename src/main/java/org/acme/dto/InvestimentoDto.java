package org.acme.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.InvestimentoClienteEntity;

import java.text.SimpleDateFormat;

@Data
@Jacksonized
public class InvestimentoDto {


    public InvestimentoDto(InvestimentoClienteEntity investimentoCliente){
        this.id = investimentoCliente.getId();
        this.tipo = investimentoCliente.getProdutoValidado().getTipo();
        this.valor = investimentoCliente.getValorInvestido();
        this.rentabilidade = investimentoCliente.getRentabilidadeEfetiva();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.data = dateFormat.format(investimentoCliente.getData());

    }

    private Long id;

    private String tipo;

    private double valor;

    private Double rentabilidade;

    private String data;
}
