package com.mobeats.api.controller;
import com.mobeats.api.model.MovimientoTipo;
import com.mobeats.api.repository.MovimientoTipoRepository;
import com.mobeats.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class MovimientoTipoController {

  @Autowired
  private MovimientoTipoRepository movimientoTipoRepository;

  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/movimiento_tipos")
  public List<MovimientoTipo> getAllMovimientoTipos() {
    return movimientoTipoRepository.findAll();
  }

  /**
   * Gets users by id.
   *
   * @param movimientoTipoId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/movimiento_tipos/{id}")
  public ResponseEntity<MovimientoTipo> getMovimientoTipoById(@PathVariable(value = "id") Long movimientoTipoId)
      throws ResourceNotFoundException {
    MovimientoTipo movimientoTipo =
        movimientoTipoRepository
            .findById(movimientoTipoId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + movimientoTipoId));
    return ResponseEntity.ok().body(movimientoTipo);
  }

  /**
   * Create user user.
   *
   * @param movimientoTipo the user
   * @return the user
   */
  @PostMapping("/movimiento_tipos")
  public MovimientoTipo createMovimientoTipo(@Valid @RequestBody MovimientoTipo movimientoTipo) {
    return movimientoTipoRepository.save(movimientoTipo);
  }

  /**
   * Update user response entity.
   *
   * @param movimientoTipoId the user id
   * @param movimientoTipoDetails the user details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/movimiento_tipos/{id}")
public ResponseEntity<MovimientoTipo> updateMovimientoTipo(
    @PathVariable(value = "id") Long movimientoTipoId, @Valid @RequestBody MovimientoTipo movimientoTipoDetails)
    throws ResourceNotFoundException {

    MovimientoTipo movimientoTipo =
        movimientoTipoRepository
            .findById(movimientoTipoId)
            .orElseThrow(() -> new ResourceNotFoundException("product not found on :: " + movimientoTipoId));

    movimientoTipo.setDescription(movimientoTipoDetails.getDescription());
    movimientoTipo.setName(movimientoTipoDetails.getName());
    final MovimientoTipo updatedMovimientoTipo = movimientoTipoRepository.save(movimientoTipo); // Cambio de variable a updatedProducto
    return ResponseEntity.ok(updatedMovimientoTipo); // Cambio de variable a updatedProducto
}

  /**
   * Delete user map.
   *
   * @param movimientoTipoId the user id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/movimiento_tipos/{id}")
  public Map<String, Boolean> deleteMovimientoTipo(@PathVariable(value = "id") Long movimientoTipoId) throws Exception {
    MovimientoTipo movimientoTipo =
        movimientoTipoRepository
            .findById(movimientoTipoId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + movimientoTipoId));

   movimientoTipoRepository.delete(movimientoTipo);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
