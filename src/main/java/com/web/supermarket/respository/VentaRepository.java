package com.web.supermarket.respository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.supermarket.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteId(Long clienteId);
    
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}

