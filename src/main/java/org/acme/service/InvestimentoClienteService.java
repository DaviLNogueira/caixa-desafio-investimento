package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.InvestimentoDto;
import org.acme.repository.InvestimentoClienteRepository;

import java.util.List;

@ApplicationScoped
public class InvestimentoClienteService {


    @Inject
    InvestimentoClienteRepository repository;


    public List<InvestimentoDto> findAllInvestimentoByClienteId(Long clienteId){
        return repository.findAllInvestimentoByClienteId(clienteId).stream().map(
            InvestimentoDto::new
        ).toList();
    }
}
