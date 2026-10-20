package com.web.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name ="producto")
@Data 
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false, length = 100)
    private double precio;

    @Column (nullable = false, length = 100)
    private int stock;

    @Column (name = "codigo_barras", nullable = false, unique = true, length = 50)
    private String codigoBarras;

    @ManyToOne
    @JoinColumn (name="categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne 
    @JoinColumn (name="proveedor_id", nullable = false)
    private Proveedor proveedor;
}
