package com.mobeats.api.controller;
import com.mobeats.api.model.Producto;
import com.mobeats.api.repository.ProductoRepository;
import com.mobeats.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User controller.
 *
 * @author Givantha Kalansuriya
 */
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

  @Autowired
  private ProductoRepository productoRepository;

  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/productos")
  public List<Producto> getAllUsers() {
    return productoRepository.findAll();
  }

  /**
   * Gets users by id.
   *
   * @param productoId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/productos/{id}")
  public ResponseEntity<Producto> getUsersById(@PathVariable(value = "id") Long productoId)
      throws ResourceNotFoundException {
    Producto producto =
        productoRepository
            .findById(productoId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + productoId));
    return ResponseEntity.ok().body(producto);
  }

  /**
   * Create user user.
   *
   * @param producto the user
   * @return the user
   */
  @PostMapping("/productos")
  public Producto createUser(@Valid @RequestBody Producto producto) {
    return productoRepository.save(producto);
  }

  /**
   * Update user response entity.
   *
   * @param productoId the user id
   * @param productoDetails the user details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/productos/{id}")
  public ResponseEntity<Producto> updateUser(
      @PathVariable(value = "id") Long productoId, @Valid @RequestBody Producto productoDetails)
      throws ResourceNotFoundException {

    Producto producto =
        productoRepository
            .findById(productoId)
            .orElseThrow(() -> new ResourceNotFoundException("product not found on :: " + productoId));

    producto.setDescription(productoDetails.getDescription());
    producto.setName(productoDetails.getName());
    producto.setUpdatedAt(new Date());
    final Producto updatedUser = productoRepository.save(producto);
    return ResponseEntity.ok(updatedUser);
  }

  /**
   * Delete user map.
   *
   * @param productoId the user id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/productos/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long productoId) throws Exception {
    Producto producto =
        productoRepository
            .findById(productoId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productoId));

    productoRepository.delete(producto);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}