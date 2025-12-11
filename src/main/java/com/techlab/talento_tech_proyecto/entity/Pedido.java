package com.techlab.talento_tech_proyecto.entity;

import com.techlab.talento_tech_proyecto.dto.ProductoDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nombreCliente;
  //  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime fecha;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EstadoPedido estado;

  @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
      CascadeType.REMOVE}, orphanRemoval = true)
  private List<LineaPedido> lineasPedido = new ArrayList<>();


}
