package com.web.supermarket.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name="cliente")
@Data 
public class Cliente {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 100) 
    private String nombre;

    @Column (nullable = false, unique = true, length = 50)
    private String documento;

    @Column
    private String telefono;

 @OneToMany (mappedBy ="cliente", cascade = CascadeType.ALL)
 private List<Venta> ventas;
}
