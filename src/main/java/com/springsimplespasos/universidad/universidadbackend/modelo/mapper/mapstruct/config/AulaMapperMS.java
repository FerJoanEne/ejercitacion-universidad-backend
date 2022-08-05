package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AulaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AulaMapperMS {

    AulaDTO mapAula(Aula aula);

}
