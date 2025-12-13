package com.techlab.talento_tech_proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lineas_pedidos")
public class LineaPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private double precio;
  private int cantidad;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  public LineaPedido(int cantidad, double precio, Producto producto) {
    this.precio = precio;
    this.cantidad = cantidad;
    this.producto = producto;

  }
}
