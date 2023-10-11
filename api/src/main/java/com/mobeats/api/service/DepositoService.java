package com.mobeats.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobeats.api.model.Deposito;
import com.mobeats.api.repository.DepositoRepository;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;
    
    public List<Deposito> findAll() {
    return depositoRepository.findAll();
  }
    
}
