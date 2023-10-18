package com.mobeats.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "movimientos_tipos")
@EntityListeners(AuditingEntityListener.class)
public class MovimientoTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "saldo", nullable = false)
    @Enumerated(EnumType.STRING)
    private SaldoEnum saldo;
    public enum SaldoEnum {
        ACREEDOR,
        DEUDOR
    }

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
  public String getName() {
        return nombre;
    }

  /**
   * Sets nombre.
   *
   * @param nombre the nombre
   */
  public void setName(String nombre) {
        this.nombre = nombre;
    }

  /**
   * Gets descripcion.
   *
   * @return the descripcion
   */
  public String getDescription() {
        return descripcion;
    }

  /**
   * Sets descripcion.
   *
   * @param descripcion the descripcion
   */
  public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }
        /**
     * Gets saldo.
     *
     * @return the saldo
     */
    public SaldoEnum getSaldo() {
        return saldo;
    }

    /**
     * Sets saldo.
     *
     * @param saldo the saldo
     */
    public void setSaldo(SaldoEnum saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "MovimientoTipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", saldo='" + saldo + '\'' +
                '}';
    }
}