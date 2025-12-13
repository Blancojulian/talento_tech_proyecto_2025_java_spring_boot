package com.techlab.talento_tech_proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateLineaPedidoDto {
  private long productoId;
  private int cantidad;
}
