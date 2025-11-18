package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProdutoValidadoDto;
import org.acme.entity.PerfilEntity;
import org.acme.exceptions.PerfilNaoValidoException;
import org.acme.repository.ProdutoValidadoRepository;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoValidadoRepository repository;

    public List<ProdutoValidadoDto> buscarPordutosPorRisco(String perfil){

        PerfilEntity perfilEnum ;
        try{
            perfilEnum = PerfilEntity.valueOf(perfil.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new PerfilNaoValidoException(perfil);
        }

        return repository.buscarPorRisco(perfilEnum).stream().map(
                ProdutoValidadoDto::new
        ).toList();
    }
}
