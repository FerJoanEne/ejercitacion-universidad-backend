package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pabellones")
public class PabellonController extends GenericController<Pabellon, PabellonDAO> {

    @Autowired
    public PabellonController(PabellonDAO service) {
        super(service);
        nombreEntidad = "Pabellon";
    }

    @GetMapping("/localidad")
    public ResponseEntity<?> buscarPabellonesPorLocalidad(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();

        List<Pabellon> pabellones = (List<Pabellon>) service.findPabellonByDireccion_localidad(nombre);
        if(pabellones.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen pabellones en localidad: %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", pabellones);

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/")
    public ResponseEntity<?> buscarPabellonPorNombreIgnoreCase(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();

        List<Pabellon> pabellonByNombre = (List<Pabellon>) service.findPabellonByNombreIgnoreCase(nombre);
        if(pabellonByNombre.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen pabellones con nombre: %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", pabellonByNombre);

        return ResponseEntity.ok(mensaje);
    }


}
