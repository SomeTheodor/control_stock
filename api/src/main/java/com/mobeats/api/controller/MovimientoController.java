package com.mobeats.api.controller;

import com.mobeats.api.model.Movimiento;
import com.mobeats.api.service.MovimientoService;
import com.mobeats.api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * The type User controller.
 *
 * @author Givantha Kalansuriya
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class MovimientoController {

  @Autowired
  private MovimientoService movimientoService;

  @GetMapping("/movimientos")
  public List<Movimiento> getAllMovimientos() {
      return movimientoService.getAllMovimientos();
  }
  
  @GetMapping("/movimientos/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable(value = "id") Long movimientoId)
            throws ResourceNotFoundException {
        Movimiento movimiento = movimientoService.getMovimientoById(movimientoId);
        return ResponseEntity.ok().body(movimiento);
  }

  @PostMapping("/movimientos")
    public Movimiento createMovimiento(@Valid @RequestBody Movimiento movimiento) {
      return movimientoService.createMovimiento(movimiento);
  }
}