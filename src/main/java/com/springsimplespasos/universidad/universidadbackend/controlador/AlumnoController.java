package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name ="controller.enable-dto", havingValue = "false")
public class AlumnoController extends PersonaController{

    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody Persona alumno){
        Map<String, Object> mensaje = new HashMap<>();
        Persona alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if(!oAlumno.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Alumno con ID %s no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        alumnoUpdate = oAlumno.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(alumnoUpdate));
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno, @PathVariable Integer idCarrera){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if(!oAlumno.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Alumno con ID %s no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if(!oCarrera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Carrera con ID %s no existe", idCarrera));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();
        ((Alumno)alumno).setCarrera(carrera);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(alumno));

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/carrera")
    public ResponseEntity<?> buscarAlumnosPorNombreCarrera(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> alumnos = (List<Persona>) ((AlumnoDAO)service).buscarAlumnosPorNombreCarrera(nombre);
        if(alumnos.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se han encontrado alumnos para la carrera %s", nombre));
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", alumnos);
        return ResponseEntity.ok(mensaje);
    }
}
