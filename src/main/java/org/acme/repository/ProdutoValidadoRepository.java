package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.PerfilEntity;
import org.acme.entity.ProdutoValidadoEntity;
import org.acme.entity.RiscoEntity;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoValidadoRepository implements PanacheRepository<ProdutoValidadoEntity> {

    public List<ProdutoValidadoEntity> buscarPorTipo(String tipo) {
        return find("tipo = ?1", tipo).list();
    }

    public List<ProdutoValidadoEntity> buscarPorRisco(PerfilEntity perfil){
        List<String> perfis = new ArrayList<>();
        perfis.add(RiscoEntity.BAIXO.name());
        switch (perfil) {
            case MODERADO -> perfis.add(RiscoEntity.MODERADO.name());
            case AGRESSIVO -> {
                perfis.add(RiscoEntity.MODERADO.name());
                perfis.add(RiscoEntity.ALTO.name());
            }
        }

        return list("risco in ?1",perfis);
    }
}
