package org.acme.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.PerfilRiscoDto;
import org.acme.service.PerfiRiscoService;

@Path("/perfil-risco/")
@Authenticated
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilRiscoController {

    @Inject
    PerfiRiscoService service;

    @GET
    @Path("/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public PerfilRiscoDto findByClinetId(Long clienteId){
        return service.findbyIdCliente(clienteId);
    }
}
