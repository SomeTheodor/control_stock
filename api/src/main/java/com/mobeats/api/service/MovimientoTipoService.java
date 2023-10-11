package com.mobeats.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobeats.api.model.MovimientoTipo;
import com.mobeats.api.repository.MovimientoTipoRepository;

@Service
public class MovimientoTipoService {

    @Autowired
    private MovimientoTipoRepository movimientoTipoRepository;
    
    public List<MovimientoTipo> findAll() {
    return movimientoTipoRepository.findAll();
  }
    
}
