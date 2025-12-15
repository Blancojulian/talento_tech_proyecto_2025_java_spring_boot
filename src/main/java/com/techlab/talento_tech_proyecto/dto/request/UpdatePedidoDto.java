package com.techlab.talento_tech_proyecto.dto.request;

import com.techlab.talento_tech_proyecto.entity.EstadoPedido;
import jakarta.annotation.Nullable;
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
  @Nullable
  private EstadoPedido estado;
  private List<CreateLineaPedidoDto> lineasPedido = new ArrayList<>();
}
