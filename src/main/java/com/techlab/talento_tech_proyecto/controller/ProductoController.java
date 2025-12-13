package com.techlab.talento_tech_proyecto.controller;


import com.techlab.talento_tech_proyecto.dto.CreateProductoDto;
import com.techlab.talento_tech_proyecto.dto.ProductoDto;
import com.techlab.talento_tech_proyecto.dto.UpdateProductoDto;
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
@RequestMapping("/productos")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping()
  public List<ProductoDto> getAll(@RequestParam(required = false, defaultValue = "") String nombre,
      @RequestParam(required = false, defaultValue = "0") Double precio) {
    return productoService.getAll(nombre, precio);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable long id) {
    return ResponseEntity.ok(productoService.get(id));
  }

  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody CreateProductoDto producto) {
    var p = this.productoService.create(producto);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody UpdateProductoDto producto) {
    var p = this.productoService.update(id, producto);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    this.productoService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
