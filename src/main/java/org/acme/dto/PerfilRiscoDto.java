package org.acme.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.PerfilRiscoEntity;

@Data
@Jacksonized
public class PerfilRiscoDto {


    public PerfilRiscoDto(PerfilRiscoEntity cliente){
        this.clienteId = cliente.getClienteId();
        this.perfil = cliente.getPerfil().name();
        this.descricao = cliente.getDescricao();
        this.pontuacao = cliente.getPontuacao();
    }

    private Long clienteId;

    private String perfil;

    private Integer pontuacao;

    private String descricao;



}
