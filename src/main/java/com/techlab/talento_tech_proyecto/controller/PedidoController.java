package com.techlab.talento_tech_proyecto.controller;

import com.techlab.talento_tech_proyecto.dto.CreatePedidoDto;
import com.techlab.talento_tech_proyecto.dto.CreateProductoDto;
import com.techlab.talento_tech_proyecto.dto.ProductoDto;
import com.techlab.talento_tech_proyecto.dto.UpdatePedidoDto;
import com.techlab.talento_tech_proyecto.dto.UpdateProductoDto;
import com.techlab.talento_tech_proyecto.entity.Pedido;
import com.techlab.talento_tech_proyecto.service.PedidoService;
import com.techlab.talento_tech_proyecto.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoService pedidoService;

  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }


  @GetMapping()
  public List<Pedido> getAll(@RequestParam(required = false, defaultValue = "") String nombre) {
    return pedidoService.getAll(nombre);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable long id) {
    return ResponseEntity.ok(pedidoService.get(id));
  }

  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody CreatePedidoDto pedido) {
    var p = this.pedidoService.create(pedido);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody UpdatePedidoDto pedido) {
    var p = this.pedidoService.update(id, pedido);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    this.pedidoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
