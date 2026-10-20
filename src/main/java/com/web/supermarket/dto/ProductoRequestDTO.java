package com.web.supermarket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class ProductoRequestDTO {
    
    @NotBlank (message = "El nombre del producto es obligatorio")
    private String nombre;

    @NotNull (message = "El precio  es obligatorio")
    @Positive (message = "El precio del producto debe ser mayor a 0")
    private double precio;

    @NotNull (message = "El stock es obligatorio")
    @Min (value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotBlank (message = "El codigo de barras es obligatorio")
    private String codigoBarras;

    @NotNull (message = "El id de la categoria es obligatorio")
    private long categoriaId;

    @NotNull (message = "El id del proveedor es obligatorio")
    private long proveedorId;
}
