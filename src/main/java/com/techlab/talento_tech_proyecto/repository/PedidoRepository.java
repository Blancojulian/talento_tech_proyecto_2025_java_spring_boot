package com.techlab.talento_tech_proyecto.repository;

import com.techlab.talento_tech_proyecto.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
