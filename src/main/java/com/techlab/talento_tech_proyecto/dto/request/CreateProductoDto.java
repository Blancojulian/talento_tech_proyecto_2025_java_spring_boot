package com.techlab.talento_tech_proyecto.dto.request;

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
public class CreateProductoDto{

  @NotBlank
  String nombre;
  @Size(min = 2, max = 500)
  String descripcion;
  @NotBlank(message = "Debe enviar la categoria del producto")
  String categoria;
  @NotBlank(message = "Debe enviar la url de la imagen del producto")
  String urlImg;
  double precio;
  @Min(value = 0, message = "El stock no puede ser un valor negativo")
  int stock;
}