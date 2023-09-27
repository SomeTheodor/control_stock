package com.mobeats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobeats.model.Producto;
import com.mobeats.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> findAll() {
    return productoRepository.findAll();
  }
    
}