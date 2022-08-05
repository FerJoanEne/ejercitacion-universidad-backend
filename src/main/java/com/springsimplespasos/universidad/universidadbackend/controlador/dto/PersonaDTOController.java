package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.EmpleadoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.ProfesorMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;

public class PersonaDTOController extends GenericDTOController<Persona, PersonaDAO> {

    protected final AlumnoMapper alumnoMapper;
    protected final ProfesorMapper profesorMapper;
    protected final EmpleadoMapper empleadoMapper;


    public PersonaDTOController(PersonaDAO service, String nombreEntidad, AlumnoMapper alumnoMapper, ProfesorMapper profesorMapper, EmpleadoMapper empleadoMapper) {
        super(service, nombreEntidad);
        this.alumnoMapper = alumnoMapper;
        this.profesorMapper = profesorMapper;
        this.empleadoMapper = empleadoMapper;
    }

    public PersonaDTO altaPersona(Persona persona){
        Persona altaEntidad = super.altaEntidad(persona);
        PersonaDTO dto = null;
        if(altaEntidad instanceof Alumno){
            dto = alumnoMapper.mapAlumno((Alumno) altaEntidad);
        } else if (altaEntidad instanceof Profesor) {
            dto = profesorMapper.mapProfesor((Profesor) altaEntidad);
        } else if (altaEntidad instanceof Empleado) {
            dto = empleadoMapper.mapEmpleado((Empleado) altaEntidad);
        }

        return dto;
    }
}

