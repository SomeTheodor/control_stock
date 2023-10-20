package com.mobeats.api.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "descripcion", nullable = false)
  private String descripcion;

  @Column(name = "precio", nullable = false)
  private Float precio;

  @Column(name = "cantidad", nullable = false)
  private int cantidad;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date fecha_creacion = new Date();

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_actualizacion", nullable = false)
  private Date fecha_actualizacion;

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

  /**
   * Gets descripcion.
   *
   * @return the descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Sets descripcion.
   *
   * @param descripcion the descripcion
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Gets precio.
   *
   * @return the precio
   */
  public Float getPrecio() {
    return precio;
  }

  /**
   * Sets precio.
   *
   * @param precio the precio
   */
  public void setPrecio(Float precio) {
    this.precio = precio;
  }

  /**
   * Gets cantidad.
   *
   * @return the cantidad
   */
  public Integer getCantidad() {
    return cantidad;
  }

  /**
   * Sets cantidad.
   *
   * @param cantidad the cantidad
   */
  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  /**
   * Gets fecha_creacion.
   *
   * @return the fecha_creacion
   */
  public Date getFechaCreacion() {
    return fecha_creacion;
  }

  /**
   * Sets fecha_creacion.
   *
   * @param fecha_creacion the fecha_creacion
   */
  public void setFechaCreacion(Date fecha_creacion) {
    this.fecha_creacion = fecha_creacion;
  }

  /**
   * Gets fecha_actualizacion.
   *
   * @return the fecha_actualizacion
   */
  public Date getFechaActualizacion() {
    return fecha_actualizacion;
  }

  /**
   * Sets fecha_actualizacion.
   *
   * @param fecha_actualizacion the fecha_actualizacion
   */
  public void setFechaActualizacion(Date fecha_actualizacion) {
    this.fecha_actualizacion = fecha_actualizacion;
  }

  @Override
  public String toString() {
    return "Productos{" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        ", descripcion='" + descripcion + '\'' +
        ", precio='" + precio + '\'' +
        ", cantidad=" + cantidad +
        ", fecha_creacion='" + fecha_creacion + '\'' +
        ", fecha_actualizacion=" + fecha_actualizacion + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Producto producto = (Producto) o;
    return Objects.equals(id, producto.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}