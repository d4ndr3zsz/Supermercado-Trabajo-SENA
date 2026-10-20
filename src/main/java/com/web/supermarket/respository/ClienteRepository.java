package com.web.supermarket.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.supermarket.entity.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

    Optional<Cliente> findByDocumento(String documento);
    
}
