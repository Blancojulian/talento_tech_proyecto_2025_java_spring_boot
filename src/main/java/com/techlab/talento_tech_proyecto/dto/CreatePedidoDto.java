package com.techlab.talento_tech_proyecto.dto;

import com.techlab.talento_tech_proyecto.entity.EstadoPedido;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
