package org.acme.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.acme.exceptions.PerfilNaoIdentificadoException;

@Entity
@Table(name = "perfil_cliente")
@Getter
@NoArgsConstructor
public class PerfilRiscoEntity {

    public PerfilRiscoEntity(Long clienteId , PerfilEntity perfil , Integer pontuacao){
        this.clienteId = clienteId;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long clienteId;

    @Enumerated(EnumType.STRING)
    private PerfilEntity perfil;

    private Integer pontuacao;

    @Transactional
    public String getDescricao(){
        switch (this.perfil){
            case CONSERVADOR -> {
                return "Perfil que realiza baixa movimentação, foco em liquidez";
            }
            case MODERADO -> {
                return "Perfil equilibrado entre segurança e rentabilidade";
            }
            case AGRESSIVO -> {
                return "Perfil que busca por alta rentabilidade, maior risco";
            }
        }
        throw new PerfilNaoIdentificadoException();
    }

    @Transactional
    public void atualizarPontuacao(Integer pontuacao, PerfilEntity perfil){
        this.pontuacao = pontuacao;
        this.perfil = perfil;
    }

}
