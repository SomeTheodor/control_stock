package com.mobeats.api.controller;
import com.mobeats.api.model.Producto;
import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.service.ProductoService;

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
public class ProductoController {

  @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable(value = "id") Long productoId)
            throws ResourceNotFoundException {
        Producto producto = productoService.getProductoById(productoId);
        return ResponseEntity.ok().body(producto);
    }

    @PostMapping("/productos")
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoService.createProducto(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable(value = "id") Long productoId,
            @Valid @RequestBody Producto productoDetails)
            throws ResourceNotFoundException {
        Producto updatedProducto = productoService.updateProducto(productoId, productoDetails);
        return ResponseEntity.ok(updatedProducto);
    }
}