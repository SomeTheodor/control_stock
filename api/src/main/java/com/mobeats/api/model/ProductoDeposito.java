package com.mobeats.api.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "productos_depositos")
@EntityListeners(AuditingEntityListener.class)
public class ProductoDeposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fecha_actualizacion;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_deposito", referencedColumnName = "id")
    private Deposito deposito;

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
   * Gets cantidad.
   *
   * @return the cantidad
   */
  public Long getCantidad() {
        return cantidad;
    }

  /**
   * Sets cantidad.
   *
   * @param cantidad the cantidad
   */
  public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
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

    public Producto getProducto() {
        return producto;
    }
      
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Deposito getDeposito() {
        return deposito;
    }
      
    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", fecha_actualizacion=" + fecha_actualizacion + '\'' +
                ", producto=" + producto +
                ", deposito=" + deposito +
                '}';
    }
}