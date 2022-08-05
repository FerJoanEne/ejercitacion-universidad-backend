package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/profesores")
public class ProfesorController extends PersonaController{

    private CarreraDAO carreraDAO;

    @Autowired
    public ProfesorController(@Qualifier("profesorDAOImpl") PersonaDAO service, CarreraDAO carreraDAO) {
        super(service);
        nombreEntidad = "Profesor";
        this.carreraDAO = carreraDAO;
    }

    @PutMapping("/{idProfesor}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarProfesorACarrera(@PathVariable Integer idProfesor, @PathVariable Integer idCarrera){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oProfesor = service.findById(idProfesor);
        if(!oProfesor.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Profesor con ID %s no existe", idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if(!oCarrera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Carrera con ID %s no existe", idCarrera));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona profesor = oProfesor.get();
        Carrera carrera = oCarrera.get();
        Set<Carrera> carreras = ((Profesor)profesor).getCarreras();
        carreras.add(carrera);
        ((Profesor)profesor).setCarreras(carreras);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(profesor));

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/carrera")
    public ResponseEntity<?> buscarProfesorPorCarrera(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> profesores = (List<Persona>) ((ProfesorDAO)service).findProfesoresByCarrera(nombre);
        if(profesores.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No hay profesores para la carrera %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", profesores);
        return ResponseEntity.ok(mensaje);
    }

}
