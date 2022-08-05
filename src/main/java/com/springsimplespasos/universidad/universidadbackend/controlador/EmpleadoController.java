package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends PersonaController {

    private PabellonDAO pabellonDAO;

    @Autowired
    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        nombreEntidad = "Empleado";
        this.pabellonDAO = pabellonDAO;
    }

    @GetMapping("/tipo")
    public ResponseEntity<?> buscarEmpleadosPorTipo(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();
        if(!esTipoEmpleado(nombre.toUpperCase())){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro el tipo %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<Persona> empleados = (List<Persona>) ((EmpleadoDAO)service).findEmpleadoByTipoEmpleado(TipoEmpleado.valueOf(nombre.toUpperCase()));
        if(empleados.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen empleados del tipo %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", empleados);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{idEmpleado}/pabellon/{idPabellon}")
    public ResponseEntity<?> asignarEmpleadoAPabellon(@PathVariable Integer idEmpleado, @PathVariable Integer idPabellon){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if(!oEmpleado.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado empleado con ID %d",idEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Optional<Pabellon> oPabellon = pabellonDAO.findById(idPabellon);
        if(!oPabellon.isPresent()){

            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado pabellon con ID %d",idPabellon));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona empleado = oEmpleado.get();
        Pabellon pabellon = oPabellon.get();
        ((Empleado)empleado).setPabellon(pabellon);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(empleado));
        return ResponseEntity.ok(mensaje);
    }

    private boolean esTipoEmpleado(String tipo){
        try {
            TipoEmpleado.valueOf(tipo.toUpperCase());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
