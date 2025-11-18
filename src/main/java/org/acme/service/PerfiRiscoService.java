package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.PerfilRiscoDto;
import org.acme.entity.PerfilRiscoEntity;
import org.acme.repository.PerfilRiscoRepository;


@ApplicationScoped
public class PerfiRiscoService {

    @Inject
    PerfilRiscoRepository clienteRepository;

    public PerfilRiscoDto findbyIdCliente(Long id) {
        PerfilRiscoEntity cliente;
        cliente = clienteRepository.findByClienteId(id);
        return new PerfilRiscoDto(cliente);
    }
}
