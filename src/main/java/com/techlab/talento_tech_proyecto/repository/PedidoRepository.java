package com.techlab.talento_tech_proyecto.repository;

import com.techlab.talento_tech_proyecto.entity.Pedido;
import com.techlab.talento_tech_proyecto.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  List<Pedido> findByNombreClienteContainingIgnoreCase(String nombre);

}
