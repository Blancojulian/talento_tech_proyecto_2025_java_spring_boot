package com.techlab.talento_tech_proyecto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductoDto {

  String nombre;
  String descripcion;
  String categoria;
  String urlImg;
  double precio;
  int stock;
}
