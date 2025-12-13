package com.techlab.talento_tech_proyecto.service;

import com.techlab.talento_tech_proyecto.dto.request.CreateLineaPedidoDto;
import com.techlab.talento_tech_proyecto.dto.request.CreatePedidoDto;
import com.techlab.talento_tech_proyecto.dto.response.LineaPedidoDto;
import com.techlab.talento_tech_proyecto.dto.response.PedidoDto;
import com.techlab.talento_tech_proyecto.dto.request.UpdatePedidoDto;
import com.techlab.talento_tech_proyecto.entity.LineaPedido;
import com.techlab.talento_tech_proyecto.entity.Pedido;
import com.techlab.talento_tech_proyecto.exception.NotFoundException;
import com.techlab.talento_tech_proyecto.exception.StockInsuficienteException;
import com.techlab.talento_tech_proyecto.repository.LineaPedidoRepository;
import com.techlab.talento_tech_proyecto.repository.PedidoRepository;
import com.techlab.talento_tech_proyecto.repository.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepo;
  private final LineaPedidoRepository lineaPedidoRepo;
  private final ProductoRepository productoRepo;

  public PedidoService(PedidoRepository pedidoRepo, LineaPedidoRepository lineaPedidoRepo, ProductoRepository productoRepo) {
    this.pedidoRepo = pedidoRepo;
    this.lineaPedidoRepo = lineaPedidoRepo;
    this.productoRepo = productoRepo;
  }

  public List<PedidoDto> getAll(String nombre) {
    List<Pedido> lista;
    if (!nombre.isBlank()) {
      lista = pedidoRepo.findByNombreClienteContainingIgnoreCase(nombre);
    } else {
      lista = pedidoRepo.findAll();
    }
    return lista.stream().map((p)->this.mapearPedido(p))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  public PedidoDto get(Long id) {
    var p = pedidoRepo.findById(id)
        .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    return this.mapearPedido(p);
  }

  public PedidoDto create(CreatePedidoDto pedidoDto) {
    var pedido = new Pedido(pedidoDto);
    var lineas = this.crearLineasPedido(pedidoDto.getLineasPedido(), true);
    pedidoRepo.save(pedido);
    lineas.forEach((lp)->lp.setPedido(pedido));
    lineaPedidoRepo.saveAll(lineas);
    return this.mapearPedido(pedido);
    //ver si funciona
//    var pedido = new Pedido(pedidoDto);
//    var lineas = this.crearLineasPedido(pedidoDto.getLineasPedido(), true);
//    pedido.setLineasPedido(lineas);
//    pedidoRepo.save(pedido);
//    return pedido;
  }

  private List<LineaPedido> crearLineasPedido(List<CreateLineaPedidoDto> lineas, boolean controlarStock){
    List<LineaPedido> lista = new ArrayList<>();
    lineas.forEach(l->{
      var producto = this.productoRepo.findById(l.getProductoId())
          .orElseThrow(()->new NotFoundException("No se encontro el producto " + l.getProductoId()));

      if (controlarStock && l.getCantidad() > producto.getStock()){
        throw new StockInsuficienteException("Stock insuficiente del producto "+producto.getNombre());
      }
      lista.add(new LineaPedido(l.getCantidad(), producto.getPrecio(), producto));

    });
    return lista;
  }

  public Pedido update(Long id, UpdatePedidoDto pedidoDto) {
    Pedido existente = pedidoRepo.findById(id)
        .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

    var lineas = this.crearLineasPedido(pedidoDto.getLineasPedido(), false);

    if (pedidoDto.getNombreCliente() != null && !pedidoDto.getNombreCliente().isBlank()) {
      existente.setNombreCliente(pedidoDto.getNombreCliente());
    }
    if (pedidoDto.getFecha() != null) {
      existente.setFecha(pedidoDto.getFecha());
    }
//    if (pedidoDto.getEstado() != null && !pedidoDto.getEstado().name()) {
//      existente.setEstado(pedidoDto.getEstado());
//    }
    this.lineaPedidoRepo.deleteAll(existente.getLineasPedido());
    existente.getLineasPedido().clear();
    lineas.forEach(lp -> {
      lp.setPedido(existente);
      existente.getLineasPedido().add(lp);
    });

    return pedidoRepo.save(existente);
  }

  public void delete(Long id) {
    this.pedidoRepo.deleteById(id);
  }
  private PedidoDto mapearPedido(Pedido pedido){
    var pedidoDto = new PedidoDto();
    pedidoDto.setId(pedido.getId());
    pedidoDto.setNombreCliente(pedido.getNombreCliente());
    pedidoDto.setFecha(pedido.getFecha());
    pedidoDto.setEstado(pedido.getEstado());
    var lineas = pedido.getLineasPedido().stream()
        .map((lp)->new LineaPedidoDto(lp))
        .collect(Collectors.toCollection(ArrayList::new));
    pedidoDto.setLineasPedido(lineas);
    return pedidoDto;
  }
}
