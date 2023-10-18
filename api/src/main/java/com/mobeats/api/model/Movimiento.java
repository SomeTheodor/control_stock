package com.mobeats.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movimientos")
@EntityListeners(AuditingEntityListener.class)
public class Movimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "cantidad", nullable = false)
  private Long cantidad;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Producto producto;

  @ManyToOne
  @JoinColumn(name = "id_movimiento_tipo", referencedColumnName = "id")
  private MovimientoTipo movimientoTipo;

  @ManyToOne
  @JoinColumn(name = "id_deposito", referencedColumnName = "id")
  private Deposito deposito;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date fecha = new Date();

public long getId() {
  return id;
}

public void setId(long id) {
  this.id = id;
}

public Long getCantidad() {
  return cantidad;
}

public void setCantidad(Long cantidad) {
  this.cantidad = cantidad;
}

public Producto getProducto() {
  return producto;
}

public void setProducto(Producto producto) {
  this.producto = producto;
}

public MovimientoTipo getMovimientoTipo() {
  return movimientoTipo;
}

public void setMovimientoTipo(MovimientoTipo movimientoTipo) {
  this.movimientoTipo = movimientoTipo;
}

public Deposito getDeposito() {
  return deposito;
}

public void setDeposito(Deposito deposito) {
  this.deposito = deposito;
}

public Date getFecha() {
  return fecha;
}

public void setFecha(Date fecha) {
  this.fecha = fecha;
}

  @Override
  public String toString() {
    return "Movimiento{" +
        "id=" + id +
        ", cantidad=" + cantidad +
        ", producto=" + producto +
        ", movimientoTipo=" + movimientoTipo +
        ", deposito=" + deposito +
        ", fecha=" + fecha + '\'' +
        '}';
  }
}