package com.techlab.talento_tech_proyecto.entity;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;
}
