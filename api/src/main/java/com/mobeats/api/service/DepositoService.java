package com.mobeats.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.Deposito;
import com.mobeats.api.repository.DepositoRepository;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<Deposito> getAllDepositos() {
        return depositoRepository.findAll();
    }

    public Deposito getDepositoById(Long depositoId) {
        return depositoRepository.findById(depositoId)
                .orElseThrow(() -> new ResourceNotFoundException("Deposito not found on :: " + depositoId));
    }

    public Deposito createDeposito(Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    public Deposito updateDeposito(Long depositoId, Deposito depositoDetails) {
        Deposito deposito = getDepositoById(depositoId);
        deposito.setNombre(depositoDetails.getNombre());
        return depositoRepository.save(deposito);
    }
}