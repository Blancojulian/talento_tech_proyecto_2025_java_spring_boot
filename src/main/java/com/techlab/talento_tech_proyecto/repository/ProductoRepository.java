package com.techlab.talento_tech_proyecto.repository;


import com.techlab.talento_tech_proyecto.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findAll();

  Optional<Producto> findByNombre(String nombre);

  @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre%")
  List<Producto> buscarPorNombre(@Param("nombre") String nombre);


  List<Producto> findByStockLessThan(Integer stockMinimo);

  //---------------

  // 2. Buscar por nombre (contiene) - insensible a mayúsculas/minúsculas
  List<Producto> findByNombreContainingIgnoreCase(String nombre);

  // 3. Buscar por categoría
  List<Producto> findByCategoria(String categoria);

  // 4. Buscar por categoría y nombre
  List<Producto> findByCategoriaAndNombreContainingIgnoreCase(String categoria, String nombre);

  // 5. Buscar productos con precio menor o igual a
  List<Producto> findByPrecioLessThanEqual(double precio);

  List<Producto> findByNombreContainingIgnoreCaseAndPrecioLessThanEqual(String nombre, double precioMax);

  // 6. Buscar productos con precio entre un rango
  List<Producto> findByPrecioBetween(double precioMin, double precioMax);

  // 7. Buscar productos con stock disponible (stock > 0)
  List<Producto> findByStockGreaterThan(int stock);

  // 8. Buscar productos por múltiples categorías
  List<Producto> findByCategoriaIn(List<String> categorias);

  // 9. Buscar productos ordenados por precio ascendente
  List<Producto> findByOrderByPrecioAsc();

  // 10. Buscar productos ordenados por precio descendente
  List<Producto> findByOrderByPrecioDesc();

  // 11. Consulta personalizada con JPQL - búsqueda avanzada
  @Query("SELECT p FROM Producto p WHERE " +
      "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
      "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
      "LOWER(p.categoria) LIKE LOWER(CONCAT('%', :termino, '%'))")
  List<Producto> buscarEnTodosLosCampos(@Param("termino") String termino);

  // 12. Consulta nativa para casos complejos
  @Query(value = "SELECT * FROM productos WHERE " +
      "nombre LIKE %:termino% OR " +
      "descripcion LIKE %:termino% OR " +
      "categoria LIKE %:termino% " +
      "ORDER BY nombre ASC",
      nativeQuery = true)
  List<Producto> buscarProductosNativo(@Param("termino") String termino);

  // 13. Buscar productos con stock bajo (para alertas)
  @Query("SELECT p FROM Producto p WHERE p.stock <= :stockMinimo")
  List<Producto> findProductosConStockBajo(@Param("stockMinimo") int stockMinimo);

  // 14. Contar productos por categoría
  @Query("SELECT p.categoria, COUNT(p) FROM Producto p GROUP BY p.categoria")
  List<Object[]> contarProductosPorCategoria();

  // 15. Obtener productos más vendidos (requiere join con LineaPedido)
  @Query("SELECT p, SUM(lp.cantidad) as totalVendido " +
      "FROM Producto p " +
      "LEFT JOIN p.lineasPedido lp " +
      "GROUP BY p " +
      "ORDER BY totalVendido DESC")
  List<Object[]> findProductosMasVendidos();
}
