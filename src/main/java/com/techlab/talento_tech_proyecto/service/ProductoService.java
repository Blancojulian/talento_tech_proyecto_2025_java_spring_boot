package com.techlab.talento_tech_proyecto.service;


import com.techlab.talento_tech_proyecto.dto.request.CreateProductoDto;
import com.techlab.talento_tech_proyecto.dto.response.ProductoDto;
import com.techlab.talento_tech_proyecto.dto.request.UpdateProductoDto;
import com.techlab.talento_tech_proyecto.entity.Producto;
import com.techlab.talento_tech_proyecto.exception.NotFoundException;
import com.techlab.talento_tech_proyecto.repository.ProductoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {


  private final ProductoRepository repo;

  public ProductoService(ProductoRepository repo){
    this.repo = repo;
  }

  public ProductoDto create(CreateProductoDto producto) {
    var p = this.repo.save(new Producto(producto));
    return new ProductoDto(p);
  }

  public ProductoDto update(long id, UpdateProductoDto updateDTO){
    Producto producto = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + id));
    if (!updateDTO.getNombre().isEmpty()){
      producto.setNombre(updateDTO.getNombre());
    }
    if (!updateDTO.getDescripcion().isEmpty()) {
      producto.setDescripcion(updateDTO.getDescripcion());
    }
    if (!updateDTO.getCategoria().isEmpty()) {
      producto.setCategoria(updateDTO.getCategoria());
    }
    if (!updateDTO.getUrlImg().isEmpty()) {
      producto.setUrlImg(updateDTO.getUrlImg());
    }
    if (updateDTO.getPrecio() > 0) {
      producto.setPrecio(updateDTO.getPrecio());
    }
    if (updateDTO.getStock() > 0) {
      producto.setStock(updateDTO.getStock());
    }
    var p = this.repo.save(producto);
    return new ProductoDto(p);
  }

  public List<ProductoDto> getAll(String nombre, Double precio) {
    List<Producto> productos;
    if (!nombre.isEmpty() && precio > 0) {
      productos = this.repo.findByNombreContainingIgnoreCaseAndPrecioLessThanEqual(nombre, precio);
    } else if (!nombre.isEmpty()) {
      productos = this.repo.buscarPorNombre(nombre);
    } else if (precio > 0) {
      productos = this.repo.findByPrecioLessThanEqual(precio);
    } else {
      productos = this.repo.findAll();
    }
    return productos.stream()
        .map(ProductoDto::new)
        .collect(Collectors.toList());
  }

  public ProductoDto get(long id) {

    var p = this.repo.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));
    return new ProductoDto(p);
  }

  public void delete(long id) {
    this.repo.deleteById(id);
  }
}
