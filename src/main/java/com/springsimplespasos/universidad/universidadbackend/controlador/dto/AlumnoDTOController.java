package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AlumnoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.EmpleadoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.ProfesorMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name ="controller.enable-dto", havingValue = "true")
public class AlumnoDTOController extends PersonaDTOController {

    public AlumnoDTOController(@Qualifier("alumnoDAOImpl") PersonaDAO service, AlumnoMapper alumnoMapper, ProfesorMapper profesorMapper, EmpleadoMapper empleadoMapper) {
        super(service, "Alumno", alumnoMapper, profesorMapper, empleadoMapper);
    }

    @GetMapping
    public ResponseEntity<?> obtenerAlumnos(){
        Map<String, Object> mensaje = new HashMap<>();
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", obtenerTodos());
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlumnoById(@PathVariable Integer id){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona = service.findById(id);
        if(!oPersona.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("no existe alumno con id: %d", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO dto = alumnoMapper.mapAlumno((Alumno) oPersona.get());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dto);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> altaAlumno(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.altaPersona(alumnoMapper.mapAlumno((AlumnoDTO) personaDTO));

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", save);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

}
