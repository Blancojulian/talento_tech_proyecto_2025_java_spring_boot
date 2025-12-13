package com.techlab.talento_tech_proyecto.dto.response;

import com.techlab.talento_tech_proyecto.entity.EstadoPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PedidoDto {
  private long id;
  private String nombreCliente;
  private LocalDateTime fecha;
  private EstadoPedido estado;
  private List<LineaPedidoDto> lineasPedido = new ArrayList<>();
}
