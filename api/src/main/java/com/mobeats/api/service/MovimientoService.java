package com.mobeats.api.service;

import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.Movimiento;
import com.mobeats.api.model.MovimientoTipo;
import com.mobeats.api.model.ProductoDeposito;
import com.mobeats.api.model.Producto;
import com.mobeats.api.repository.MovimientoRepository;
import com.mobeats.api.repository.MovimientoTipoRepository;
import com.mobeats.api.repository.ProductoDepositoRepository;
import com.mobeats.api.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private MovimientoTipoRepository movimientoTipoRepository;

    @Autowired
    private ProductoDepositoRepository productoDepositoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoDepositoService productoDepositoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MovimientoTipoService movimientoTipoService;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento getMovimientoById(Long movimientoId) {
        return movimientoRepository
                .findById(movimientoId)
                .orElseThrow(() -> new ResourceNotFoundException("movimiento not found on :: " + movimientoId));
    }

    @Transactional
    public Movimiento createMovimiento(Movimiento movimiento) {
        Producto producto = productoRepository.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with ID: " + movimiento.getProducto().getId()));
    
        ProductoDeposito productoDeposito = productoDepositoRepository.findByProductoAndDeposito(producto, movimiento.getDeposito());
        MovimientoTipo movimientoTipo = movimientoTipoRepository
    .findById(movimiento.getProducto().getId())
    .orElseThrow(() -> new ResourceNotFoundException("MovimientoTipo not found with ID: " + movimiento.getProducto().getId()));

    
        Long nuevaCantidad = movimiento.getCantidad();
        Long nuevaCantidadProducto = nuevaCantidad;
    
        if ("ACREEDOR".equals(movimiento.getMovimientoTipo().getSaldo().toString())) {
            nuevaCantidad += productoDeposito.getCantidad();
            nuevaCantidadProducto += producto.getCantidad();
        } else {
            nuevaCantidad = productoDeposito.getCantidad() - nuevaCantidad;
            nuevaCantidadProducto = producto.getCantidad() - nuevaCantidad;
        }
    
        productoDepositoService.updateCantidadProductoDeposito(productoDeposito.getId(), nuevaCantidad);
        productoService.updateCantidadProducto(movimiento.getProducto().getId(), nuevaCantidadProducto);
    
        return movimientoRepository.save(movimiento);
    }
    
    
}