package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
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
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name ="controller.enable-dto", havingValue = "false")
public class AulaController extends GenericController<Aula, AulaDAO>{

    @Autowired
    public AulaController(AulaDAO service) {
        super(service);
        nombreEntidad = "Aula";
    }

    @GetMapping("/tipo")
    public ResponseEntity<?> buscarAulasPorPizarron(@RequestParam String tipo){
        Map<String, Object> mensaje = new HashMap<>();
        if(!esTipoPizarron(tipo.toUpperCase())){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No exite el tipo: -%s-", tipo.toUpperCase()));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<Aula> aulas = (List<Aula>) service.findAulasByPizarron(Pizarron.valueOf(tipo.toUpperCase()));
        if(aulas.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen aulas del tipo: -%s-", tipo));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", aulas);

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/pabellon/{nombre}")
    public ResponseEntity<?> buscarAulasPorPabellonNombre(@PathVariable String nombre){
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.findAulasByPabellon_Nombre(nombre);
        if(aulas.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen aulas pertenecientes al pabellon de nombre: -%s-", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", aulas);

        return ResponseEntity.ok(mensaje);
    }


    @GetMapping("/numero")
    public ResponseEntity<?> buscarAulaPorNumero(@RequestParam Integer numero){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Aula> oAula = service.findAulasByNroAula(numero);
        if(oAula.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe el aula numero: %d", numero));
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", oAula.get());
        return ResponseEntity.ok(mensaje);
    }

    private boolean esTipoPizarron(String tipo){
        try {
            Pizarron.valueOf(tipo.toUpperCase());
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
}
