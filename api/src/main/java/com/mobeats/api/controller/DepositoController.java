package com.mobeats.api.controller;
import com.mobeats.api.model.Deposito;
import com.mobeats.api.repository.DepositoRepository;
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
public class DepositoController {

  @Autowired
  private DepositoRepository depositoRepository;

  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/depositos")
  public List<Deposito> getAllDepositos() {
    return depositoRepository.findAll();
  }

  /**
   * Gets users by id.
   *
   * @param depositoId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/depositos/{id}")
  public ResponseEntity<Deposito> getDepositoById(@PathVariable(value = "id") Long depositoId)
      throws ResourceNotFoundException {
    Deposito deposito =
        depositoRepository
            .findById(depositoId)
            .orElseThrow(() -> new ResourceNotFoundException("Deposito not found on :: " + depositoId));
    return ResponseEntity.ok().body(deposito);
  }

  /**
   * Create user user.
   *
   * @param deposito the user
   * @return the user
   */
  @PostMapping("/depositos")
  public Deposito createDeposito(@Valid @RequestBody Deposito deposito) {
    return depositoRepository.save(deposito);
  }

  /**
   * Update user response entity.
   *
   * @param depositoId the user id
   * @param depositoDetails the user details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/depositos/{id}")
public ResponseEntity<Deposito> updateDeposito(
    @PathVariable(value = "id") Long depositoId, @Valid @RequestBody Deposito depositoDetails)
    throws ResourceNotFoundException {

    Deposito deposito =
        depositoRepository
            .findById(depositoId)
            .orElseThrow(() -> new ResourceNotFoundException("product not found on :: " + depositoId));

    deposito.setNombre(depositoDetails.getNombre());
    final Deposito updateDeposito = depositoRepository.save(deposito); // Cambio de variable a updatedProducto
    return ResponseEntity.ok(updateDeposito); // Cambio de variable a updatedProducto
}

}