package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.PerfilRiscoEntity;
import org.acme.exceptions.ClienteNaoEncontrado;

import java.util.NoSuchElementException;

@ApplicationScoped
public class PerfilRiscoRepository implements PanacheRepository<PerfilRiscoEntity> {

    public PerfilRiscoEntity findByClienteId(Long id){
        try {
            return this.find("clienteId =?1",id).list().getFirst();
        } catch (NoSuchElementException e) {
            throw new ClienteNaoEncontrado(id);
        }
    }
}
