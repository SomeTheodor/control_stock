package com.mobeats.api.service;

import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.ProductoDeposito;
import com.mobeats.api.repository.ProductoDepositoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoDepositoService {
    
    @Autowired
    private ProductoDepositoRepository productoDepositoRepository;

    public List<ProductoDeposito> getAllProductosDepositos() {
        return productoDepositoRepository.findAll();
    }

    public ProductoDeposito getProductoDepositoById(Long productoDepositoId) {
        return productoDepositoRepository
                .findById(productoDepositoId)
                .orElseThrow(() -> new ResourceNotFoundException("producto not found on :: " + productoDepositoId));
    }

    public ProductoDeposito createProductoDeposito(ProductoDeposito productoDeposito) {
        return productoDepositoRepository.save(productoDeposito);
    }

    public ProductoDeposito updateProductoDeposito(Long productoDepositoId, ProductoDeposito productoDepositoDetails) {
        ProductoDeposito productoDeposito = getProductoDepositoById(productoDepositoId);
        productoDeposito.setCantidad(productoDepositoDetails.getCantidad());
        productoDeposito.setProducto(productoDepositoDetails.getProducto());
        productoDeposito.setDeposito(productoDepositoDetails.getDeposito());
        return productoDepositoRepository.save(productoDeposito);
    }

    public ProductoDeposito updateCantidadProductoDeposito(Long productoDepositoId, Long nuevaCantidad) {
        ProductoDeposito productoDeposito = productoDepositoRepository.findById(productoDepositoId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductoDeposito not found on :: " + productoDepositoId));
        
        productoDeposito.setCantidad(nuevaCantidad);
        return productoDepositoRepository.save(productoDeposito);
    }
}