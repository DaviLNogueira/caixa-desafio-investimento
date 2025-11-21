package org.acme.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.InvestimentoDto;
import org.acme.service.InvestimentoClienteService;

import java.util.List;

@Path("/investimentos/")
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class InvestimentoController {

    @Inject
    InvestimentoClienteService service;


    @GET
    @Path("/{clienteId}")
    public List<InvestimentoDto> findAllInvestimentoByCLienteId(Long clienteId){
        return service.findAllInvestimentoByClienteId(clienteId);
    }
}
