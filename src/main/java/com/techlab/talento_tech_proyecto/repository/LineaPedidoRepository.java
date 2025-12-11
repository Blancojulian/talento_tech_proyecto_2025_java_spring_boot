package com.techlab.talento_tech_proyecto.repository;

import com.techlab.talento_tech_proyecto.entity.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {

}
