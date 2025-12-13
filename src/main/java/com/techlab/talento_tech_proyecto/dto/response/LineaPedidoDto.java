package com.techlab.talento_tech_proyecto.dto.response;

import com.techlab.talento_tech_proyecto.entity.LineaPedido;
import lombok.Data;

@Data
public class LineaPedidoDto {
  private long id;
  private double precio;
  private int cantidad;
  private long productoId;
  private String producto;

  public LineaPedidoDto(LineaPedido lp){
    this.id = lp.getId();;
    this.precio = lp.getPrecio();
    this.cantidad = lp.getCantidad();
    this.productoId = lp.getProducto().getId();
    this.producto = lp.getProducto().getNombre();
  }
}
