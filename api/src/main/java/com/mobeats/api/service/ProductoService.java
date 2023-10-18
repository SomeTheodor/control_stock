package com.mobeats.api.service;

import java.util.List;
import com.mobeats.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobeats.api.model.Producto;
import com.mobeats.api.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long productoId) {
        return productoRepository
                .findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("producto not found on :: " + productoId));
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long productoId, Producto productoDetails) {
        Producto producto = getProductoById(productoId);
        producto.setName(productoDetails.getName());
        producto.setDescription(productoDetails.getDescription());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCantidad(productoDetails.getCantidad());
        return productoRepository.save(producto);
    }

    public Producto updateCantidadProducto(Long productoId, Long nuevaCantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Producto not found on :: " + productoId));
        producto.setCantidad(nuevaCantidad);
        return productoRepository.save(producto);
    }
}

