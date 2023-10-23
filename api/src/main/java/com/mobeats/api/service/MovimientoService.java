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

import org.springframework.transaction.annotation.Transactional;

import java.util.List; // Agrega la importación de List

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

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento getMovimientoById(Long movimientoId) {
        return movimientoRepository
                .findById(movimientoId)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento not found with ID: " + movimientoId));
    }

    @Transactional
    public Movimiento createMovimiento(Movimiento movimiento) {
        Producto producto = productoRepository.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto not found with ID: " + movimiento.getProducto().getId()));

        ProductoDeposito productoDeposito = productoDepositoRepository.findByProductoAndDeposito(producto,
                movimiento.getDeposito());
        MovimientoTipo movimientoTipo = movimientoTipoRepository.findById(movimiento.getMovimientoTipo().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MovimientoTipo not found with ID: " + movimiento.getMovimientoTipo().getId()));

        Integer nuevaCantidad = movimiento.getCantidad();
        Integer nuevaCantidadProducto = nuevaCantidad;

        if ("ACREEDOR".equals(movimientoTipo.getSaldo().toString())) {
            nuevaCantidad += productoDeposito.getCantidad();
            nuevaCantidadProducto += producto.getCantidad();
        } else {
            if (productoDeposito.getCantidad() >= nuevaCantidad) {
                nuevaCantidad = productoDeposito.getCantidad() - nuevaCantidad;
                nuevaCantidadProducto = producto.getCantidad() - nuevaCantidadProducto;
            } else {
                throw new RuntimeException("Estás queriendo sacar " + nuevaCantidad
                        + " y en el depósito hay " + productoDeposito.getCantidad());
            }
        }

        productoDepositoService.updateCantidadProductoDeposito(productoDeposito.getId(), nuevaCantidad);
        productoService.updateCantidadProducto(movimiento.getProducto().getId(), nuevaCantidadProducto);

        return movimientoRepository.save(movimiento);
    }
}
