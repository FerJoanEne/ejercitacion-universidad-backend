package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PabellonDTO {

    private Integer id;
    @Positive(message = "debe ser positivo")
    private Double mts2;
    @NotBlank
    @NotEmpty(message = "Debe de ingresar un nombre")
    @Size(min = 1, max = 80)
    private String nombre;
    private Direccion direccion;
}
