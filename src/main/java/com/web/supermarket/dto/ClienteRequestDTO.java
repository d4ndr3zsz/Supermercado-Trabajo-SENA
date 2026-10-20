package com.web.supermarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombre;

    @NotBlank(message = "El documento del cliente es obligatorio")
    private String documento;

    @NotEmpty (message = "Tiene que ingresar un telefono")
    private String telefono;
}