package com.techlab.talento_tech_proyecto.service;

import com.techlab.talento_tech_proyecto.entity.Pedido;
import com.techlab.talento_tech_proyecto.exception.NotFoundException;
import com.techlab.talento_tech_proyecto.repository.LineaPedidoRepository;
import com.techlab.talento_tech_proyecto.repository.PedidoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepo;
  private final LineaPedidoRepository lineaPedidoRepo;

  public PedidoService(PedidoRepository pedidoRepo, LineaPedidoRepository lineaPedidoRepo) {
    this.pedidoRepo = pedidoRepo;
    this.lineaPedidoRepo = lineaPedidoRepo;
  }

  public List<Pedido> getAll() {
    return pedidoRepo.findAll();
  }

  public Pedido getById(Long id) {
    return pedidoRepo.findById(id)
        .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
  }

  public Pedido create(Pedido pedido) {
    // Asegurar relaciones bidireccionales
    pedido.getLineasPedido().forEach(lp -> lp.setPedido(pedido));
    return pedidoRepo.save(pedido);
  }

  public Pedido update(Long id, Pedido pedidoActualizado) {
    Pedido existente = pedidoRepo.findById(id)
        .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

    existente.setNombreCliente(pedidoActualizado.getNombreCliente());
    existente.setFecha(pedidoActualizado.getFecha());
    existente.setEstado(pedidoActualizado.getEstado());

    // Actualizar lineas
    existente.getLineasPedido().clear();
    pedidoActualizado.getLineasPedido().forEach(lp -> {
      lp.setPedido(existente);
      existente.getLineasPedido().add(lp);
    });

    return pedidoRepo.save(existente);
  }

  public void delete(Long id) {
    this.pedidoRepo.deleteById(id);
  }
}
