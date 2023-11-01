package com.mobeats.api.controller;

import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.ProductoDeposito;
import com.mobeats.api.service.ProductoDepositoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoDepositoController {

    @Autowired
    private ProductoDepositoService productoDepositoService;

    @GetMapping("/productos_depositos")
    public List<ProductoDeposito> getAllProductosDepositos() {
        return productoDepositoService.getAllProductosDepositos();
    }

    @GetMapping("/productos_depositos/{id}")
    public ResponseEntity<ProductoDeposito> getProductoDepositoById(@PathVariable(value = "id") Long productoDepositoId)
            throws ResourceNotFoundException {
        ProductoDeposito productoDeposito = productoDepositoService.getProductoDepositoById(productoDepositoId);
        return ResponseEntity.ok().body(productoDeposito);
    }
    @GetMapping("/productos_depositos/producto/{id_producto}")
    public List<ProductoDeposito> getProductosDepositosByProductoId(@PathVariable(value = "id_producto") Long productoId) {
    return productoDepositoService.getProductosDepositosByProductoId(productoId);
    }

    @PostMapping("/productos_depositos")
    public ProductoDeposito createProductoDeposito(@Valid @RequestBody ProductoDeposito productoDeposito) {
        return productoDepositoService.createProductoDeposito(productoDeposito);
    }

    @PutMapping("/productos_depositos/{id}")
    public ResponseEntity<ProductoDeposito> updateProductoDeposito(
            @PathVariable(value = "id") Long productoDepositoId,
            @Valid @RequestBody ProductoDeposito productoDepositoDetails)
            throws ResourceNotFoundException {
        ProductoDeposito updatedProductoDeposito = productoDepositoService.updateProductoDeposito(productoDepositoId, productoDepositoDetails);
        return ResponseEntity.ok(updatedProductoDeposito);
    }
}
