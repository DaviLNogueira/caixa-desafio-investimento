package org.acme.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.ProdutoDiaResponseDto;
import org.acme.dto.SimilacaoResponseDto;
import org.acme.dto.SimulacaoRequestDTO;
import org.acme.dto.SimularInvestimentoResponseDto;
import org.acme.service.SimulacaoService;

import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class SimulacaoController {

    @Inject
    SimulacaoService simulacaoService;

    @POST()
    @Path("simular-investimento")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public SimularInvestimentoResponseDto simularInvestimento(@Valid SimulacaoRequestDTO request) {
        if (request == null) {
            throw new NullPointerException("Preencha a requisição com os valores");
        }
        return simulacaoService.simularInvestimento(request);
    }

    @GET
    @Path("simulacoes")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<SimilacaoResponseDto> todasSimulacoes() {
        return simulacaoService.todasSimulacoes();
    }

    @GET
    @Path("/simulacoes/por-produto-dia")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ProdutoDiaResponseDto> simuacaoPorProdutoDia(@QueryParam("data") String data) {
        return simulacaoService.simulacaoProdutoDia(data);
    }
}
