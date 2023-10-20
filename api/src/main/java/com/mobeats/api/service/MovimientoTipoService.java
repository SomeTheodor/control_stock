package com.mobeats.api.service;

import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.MovimientoTipo;
import com.mobeats.api.repository.MovimientoTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoTipoService {

    @Autowired
    private MovimientoTipoRepository movimientoTipoRepository;

    public List<MovimientoTipo> getAllMovimientoTipos() {
        return movimientoTipoRepository.findAll();
    }

    public MovimientoTipo getMovimientoTipoById(Long movimientoTipoId) {
        return movimientoTipoRepository.findById(movimientoTipoId)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoTipo not found on :: " + movimientoTipoId));
    }

    public MovimientoTipo createMovimientoTipo(MovimientoTipo movimientoTipo) {
        return movimientoTipoRepository.save(movimientoTipo);
    }

    public MovimientoTipo updateMovimientoTipo(Long movimientoTipoId, MovimientoTipo movimientoTipoDetails) {
        MovimientoTipo movimientoTipo = getMovimientoTipoById(movimientoTipoId);
        movimientoTipo.setNombre(movimientoTipoDetails.getNombre());
        movimientoTipo.setDescripcion(movimientoTipoDetails.getDescripcion());
        movimientoTipo.setSaldo(movimientoTipoDetails.getSaldo());
        return movimientoTipoRepository.save(movimientoTipo);
    }
}