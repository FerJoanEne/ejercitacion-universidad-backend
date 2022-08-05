package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PabellonDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PabellonMapperMS {

    PabellonDTO mapPabellon(Pabellon pabellon);
}
