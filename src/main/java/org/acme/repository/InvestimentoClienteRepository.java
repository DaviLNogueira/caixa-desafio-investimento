package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.InvestimentoClienteEntity;

import java.util.List;

@ApplicationScoped
public class InvestimentoClienteRepository implements PanacheRepository<InvestimentoClienteEntity> {

    public List<InvestimentoClienteEntity> findAllInvestimentoByClienteId(Long clienteId){
        return this.find("clienteId =?1 ORDER BY id ASC", clienteId).list();
    }
}
