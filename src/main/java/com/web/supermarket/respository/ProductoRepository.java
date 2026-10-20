package com.web.supermarket.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.supermarket.entity.Producto;

@Repository 

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByStockLessThanEqual(Integer limite);

    Optional<Producto> findByCodigoBarras(String codigoBarras);
}
