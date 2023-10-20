package com.mobeats.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "depositos")
@EntityListeners(AuditingEntityListener.class)
public class Deposito {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets nombre
   *
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Sets nombre.
   *
   * @param nombre the nombre
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return "Depositos{" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Deposito deposito = (Deposito) o;
    return Objects.equals(id, deposito.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}