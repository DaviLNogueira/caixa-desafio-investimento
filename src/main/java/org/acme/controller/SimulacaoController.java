package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.SimulacaoRequestDTO;
import org.acme.dto.SimularInvestimentoResponseDto;
import org.acme.service.SimulacaoService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacaoController {

    @Inject
    SimulacaoService simulacaoService;

    @POST()
    @Path("simular-investimento")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public SimularInvestimentoResponseDto simularInvestimento(@Valid SimulacaoRequestDTO request)
    {
        if(request == null){
            throw  new NullPointerException("Preencha a requisição com os valores");
        }
        return simulacaoService.simularInvestimento(request);
    }
}
