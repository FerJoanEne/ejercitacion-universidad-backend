package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AulaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config.AulaMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name ="controller.enable-dto", havingValue = "true")
public class AulaDTOController extends GenericDTOController<Aula, AulaDAO>{


    @Autowired
    private AulaMapperMS aulaMapperMS;

    public AulaDTOController(AulaDAO service) {
        super(service, "Aula");
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = super.obtenerTodos();
        if(aulas.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron %ss cargadas", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<AulaDTO> aulasDTO = aulas
                .stream()
                .map(aulaMapperMS::mapAula)
                .collect(Collectors.toList());


        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", aulasDTO);
        return ResponseEntity.ok(mensaje);
    }
}
