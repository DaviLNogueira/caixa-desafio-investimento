package org.acme.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.MetricaPorRotaDto;
import org.acme.dto.ServicosDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


@ApplicationScoped
public class TelemetriaService {

    @Inject
    MeterRegistry registry;

    public ServicosDto coletarMetricasPorRota() {
        List<MetricaPorRotaDto> servicos = new ArrayList<>();
        Collection<Timer> timers = registry.find("http.server.requests").timers();
        for (Timer timer : timers) {
            String rota = timer.getId().getTag("uri");
            Long quantidadeChamadas = timer.count();
            Double mediaMs = Math.round(timer.mean(TimeUnit.MILLISECONDS) *100) / 100.0;
            if (rota == null || rota.startsWith("/q") || rota.contentEquals("/telemetria")) continue;
            servicos.add(
                    new MetricaPorRotaDto(
                            rota,
                            quantidadeChamadas,
                            mediaMs
                    )
            );
        }
        return new ServicosDto(servicos);
    }
}
