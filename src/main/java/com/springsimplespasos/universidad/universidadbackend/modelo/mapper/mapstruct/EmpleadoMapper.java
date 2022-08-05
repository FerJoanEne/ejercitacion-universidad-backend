package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config.ProfesorMapperConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = ProfesorMapperConfig.class)
public abstract class EmpleadoMapper {

    public abstract EmpleadoDTO mapEmpleado(Empleado empleado);
    public abstract Empleado mapEmpleado(EmpleadoDTO empleadoDTO);
}
