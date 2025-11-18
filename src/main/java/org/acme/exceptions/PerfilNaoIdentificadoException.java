package org.acme.exceptions;

public class PerfilNaoIdentificadoException extends RuntimeException{

    @Override
    public String getMessage() {
        return "O perfil não foi encontrado ou alocado corretamente.Tente novamente!";
    }
}

