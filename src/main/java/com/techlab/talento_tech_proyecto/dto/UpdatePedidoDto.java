package com.techlab.talento_tech_proyecto.dto;

import com.techlab.talento_tech_proyecto.entity.EstadoPedido;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UpdatePedidoDto {
  @NotBlank
  private String nombreCliente;
  private EstadoPedido estado;
  private List<CreateLineaPedidoDto> lineasPedido = new ArrayList<>();
}
