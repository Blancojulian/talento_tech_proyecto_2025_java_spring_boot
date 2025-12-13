package com.techlab.talento_tech_proyecto.dto.response;

import com.techlab.talento_tech_proyecto.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDto {

  private long id;
  private String nombre;
  private String descripcion;
  private String categoria;
  private String urlImg;
  private double precio;
  private int stock;

  public ProductoDto(Producto producto) {
    this.id = producto.getId();
    this.nombre = producto.getNombre();
    this.descripcion = producto.getDescripcion();
    this.categoria = producto.getCategoria();
    this.urlImg = producto.getUrlImg();
    this.precio = producto.getPrecio();
    this.stock = producto.getStock();
  }
}
