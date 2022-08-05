package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO {

    private Integer id;
    @Positive
    private Integer nroAula;
    private String medidas;
    @Pattern(regexp = "^[0-9]+$")
    private Integer cantidadPupitres;
    private Pizarron pizarron;

}
