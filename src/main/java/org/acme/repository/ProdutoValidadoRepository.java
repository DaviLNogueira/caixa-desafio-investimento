package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.SimulacaoRequestDTO;
import org.acme.entity.ProdutoValidadoEntity;

import java.util.List;

@ApplicationScoped
public class ProdutoValidadoRepository implements PanacheRepository<ProdutoValidadoEntity> {

    public List<ProdutoValidadoEntity> buscarPorTipo(String tipo) {
        return find("tipo = ?1", tipo).list();
    }
}
