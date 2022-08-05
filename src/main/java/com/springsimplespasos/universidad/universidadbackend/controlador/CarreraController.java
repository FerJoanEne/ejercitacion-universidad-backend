package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name ="controller.enable-dto", havingValue = "false")
public class CarreraController extends GenericController<Carrera, CarreraDAO> {

    @Autowired
    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer id, @RequestBody Carrera carrera){
        Map<String, Object> mensaje = new HashMap<>();
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID no existe",id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadMaterias(carrera.getCantidadMaterias());
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());

        mensaje.put("datos", service.save(carreraUpdate));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/")
    public ResponseEntity<?> buscarCarreraPorNombreContainsIgnoreCase(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarrerasByNombreContainsIgnoreCase(nombre);
        if(carreras.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se han encontrado carreras que contengan '%s'", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", carreras);

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/profesor")
    public ResponseEntity<?> buscarCarrerasPorProfesorNombreYApellido(@RequestParam String nombre,@RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
        if(carreras.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se han encontrado carreras dictadas por el profesor con nombre %s y apellido %s", nombre, apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", carreras);

        return ResponseEntity.ok(mensaje);
    }


}
