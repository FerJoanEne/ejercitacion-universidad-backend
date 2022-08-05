package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
public class PersonaController extends GenericController<Persona, PersonaDAO>{

    public PersonaController(PersonaDAO service){
        super(service);
    }

    @GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona = service.buscarPorNombreYApellido(nombre, apellido);
        if(!oPersona.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("mensaje", oPersona.get());

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/dni")
    public ResponseEntity<?> buscarPorDni(@RequestParam String dni){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona= service.buscarPorDni(dni);
        if(!oPersona.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro Persona con DNI %s", dni));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("mensaje", oPersona.get());

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/apellido")
    public ResponseEntity<?> buscarPersonaPorApellido(@RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> personas = (List<Persona>) service.buscarPersonaPorApellido(apellido);
        if(personas.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron persona con apellido %s", apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("mensaje", personas);

        return ResponseEntity.ok(mensaje);
    }


}
