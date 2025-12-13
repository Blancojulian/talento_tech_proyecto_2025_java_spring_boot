package com.techlab.talento_tech_proyecto.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePedidoDto {
  @NotBlank
  private String nombreCliente;
  private List<CreateLineaPedidoDto> lineasPedido = new ArrayList<>();
}
