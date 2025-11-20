package org.acme.exceptions;

import org.acme.entity.PerfilEntity;


public class ProdutoNaoEncontradoException extends RuntimeException {
    final private PerfilEntity perfil;


    public ProdutoNaoEncontradoException(PerfilEntity perfil) {
        this.perfil = perfil;
    }

    @Override
    public String getMessage() {
        return String.format("Não encontrados investimentos para o perfil %s no momento",perfil.name());
    }
}
