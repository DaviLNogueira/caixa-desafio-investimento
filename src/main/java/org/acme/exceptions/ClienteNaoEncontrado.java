package org.acme.exceptions;

public class ClienteNaoEncontrado extends RuntimeException {
    final private Long id;


    public ClienteNaoEncontrado(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("O cliente com o id=%d não foi encontrado",id);
    }
}
