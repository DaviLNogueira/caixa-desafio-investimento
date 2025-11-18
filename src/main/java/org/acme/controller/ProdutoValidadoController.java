package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.ProdutoValidadoDto;
import org.acme.service.ProdutoService;
import java.util.List;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoValidadoController {

    @Inject
    ProdutoService service;

    @GET
    @Path("/produtos-recomendados/{perfil}")
    public List<ProdutoValidadoDto> buscarInvesmentoPorPerfil(String perfil){
        return this.service.buscarPordutosPorRisco(perfil);
    }
}
