package org.acme.exceptions;

import org.acme.entity.PerfilEntity;

import java.util.Arrays;

public class PerfilNaoValidoException extends RuntimeException {
    final private String perfil;


    public PerfilNaoValidoException(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String getMessage() {
        return String.format("Perfil inválido: %s.Permitidos são : %s",perfil,
                Arrays.toString(PerfilEntity.values()));
    }
}

