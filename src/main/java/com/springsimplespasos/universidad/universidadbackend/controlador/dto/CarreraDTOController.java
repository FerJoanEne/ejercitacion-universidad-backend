package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.CarreraDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config.CarreraMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name ="controller.enable-dto", havingValue = "true")
@Api(value = "Acciones relacionadas con las carreras", tags = "Acciones sobre carreras")
public class CarreraDTOController extends GenericDTOController<Carrera, CarreraDAO> {

    @Autowired
    private CarreraMapperMS mapper;

    public CarreraDTOController(CarreraDAO service) {
        super(service, "Carrera");
    }

    @GetMapping
    @ApiOperation(value = "consultar todas las carreras")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ejecutado satisfactoriamente")
    })
    public ResponseEntity<?> getAll() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = super.obtenerTodos();
        if(carreras.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron %ss cargadas", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<CarreraDTO> carrerasDTO = carreras
                .stream()
                .map(mapper::mapCarrera)
                .collect(Collectors.toList());


        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", carrerasDTO);
        return ResponseEntity.ok(mensaje);
    }
}
