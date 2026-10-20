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
@Table(name="categoria")
@Data
public class Categoria {

@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(nullable = false, length = 100)
private String nombre;

@Column (nullable = false, length = 500)
private String descripcion;

@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
private List<Producto> productos;

}
