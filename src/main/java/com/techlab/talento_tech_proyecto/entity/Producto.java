package com.techlab.talento_tech_proyecto.entity;

import com.techlab.talento_tech_proyecto.dto.CreateProductoDto;
import com.techlab.talento_tech_proyecto.dto.ProductoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "productos")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nombre;
  @Column(length = 500)
  private String descripcion;
  private String categoria;
  private String urlImg;
  private double precio;
  private int stock;

  @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
  private List<LineaPedido> lineasPedido = new ArrayList<>();

  public Producto(CreateProductoDto p) {
    this.nombre = p.getNombre();
    this.descripcion = p.getDescripcion();
    this.precio = p.getPrecio();
    this.categoria = p.getCategoria();
    this.stock = p.getStock();
    this.urlImg = p.getUrlImg();
  }
}
