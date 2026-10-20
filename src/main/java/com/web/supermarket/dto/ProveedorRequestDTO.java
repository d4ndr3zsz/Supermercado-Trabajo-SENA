package com.web.supermarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String nombre;

    private String telefono;

    @Email(message = "El formato del correo electrónico no es válido")
    private String correo;
}