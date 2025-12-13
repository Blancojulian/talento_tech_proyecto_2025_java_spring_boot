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
public class UpdatePedidoDto {
  private String nombreCliente;
  private LocalDateTime fecha;
  private EstadoPedido estado;
  private List<CreateLineaPedidoDto> lineasPedido = new ArrayList<>();
}
