package org.acme.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.ServicosDto;
import org.acme.service.TelemetriaService;


@Path("/telemetria")
@Produces(MediaType.APPLICATION_JSON)
public class ServicoController {

    @Inject
    TelemetriaService telemetriaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ServicosDto coletarMetricas() {
        return telemetriaService.coletarMetricasPorRota();
    }
}
