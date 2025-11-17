package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.ProdutoDiaResponseDto;
import org.acme.entity.SimulacaoEntity;

import java.util.List;

@ApplicationScoped
public class SimulacaoRepository  implements PanacheRepository<SimulacaoEntity> {

    public List<ProdutoDiaResponseDto> agruparProdutoDia() {
        return getEntityManager().createQuery(
                "SELECT "+
                        "   s.produto.nome, " +
                        "   FUNCTION('FORMAT', s.dataSimulacao, 'yyyy-dd-MM'), " +
                        "   COUNT(s), " +
                        "   AVG(s.valorFinal)" +
                        "FROM org.acme.entity.SimulacaoEntity s " +
                        "GROUP BY s.produto.nome, FUNCTION('FORMAT', s.dataSimulacao, 'yyyy-dd-MM') " +
                        "ORDER BY s.produto.nome",
                ProdutoDiaResponseDto.class
        ).getResultList();
    }
}
