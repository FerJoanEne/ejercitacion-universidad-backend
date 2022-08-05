package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericoDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
public class GenericController<E,S extends GenericoDAO<E>> {

    protected final S service;
    protected  String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        Map<String,Object> mensaje = new HashMap<>();
        List<E> listado = (List<E>) service.findAll();
        if(listado.isEmpty()){
            /*throw new BadRquestException(String.format("No se han encontrado %ss", nombreEntidad));*/
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se han encontrado %ss", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", listado);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenderPorId(@PathVariable(required = false) Integer id){
        Map<String,Object> mensaje = new HashMap<>();
        Optional<E> entidad = (Optional<E>) service.findById(id);
        if(!entidad.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado %ss", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", entidad.get());
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> altaEntidad(@Valid @RequestBody E entidad, BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();

        if(result.hasErrors()){
            result.getFieldErrors().forEach(error->validaciones.put(error.getField(),error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }

        return ResponseEntity.ok(service.save(entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id){
        Map<String,Object> mensaje = new HashMap<>();
        service.deleteById(id);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);

    }

}
