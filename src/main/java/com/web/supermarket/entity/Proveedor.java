package com.web.supermarket.entity;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name="proveedor")
@Data 
public class Proveedor {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 100)
    private String nombre ;

    @Column (nullable = false, length = 10)
    private String telefono;

    @Column (unique = true)
    private String correo;

    @OneToMany (mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Producto> productos;
}
