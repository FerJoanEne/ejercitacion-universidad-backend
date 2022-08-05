package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AulaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-31T21:05:43-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class AulaMapperMSImpl implements AulaMapperMS {

    @Override
    public AulaDTO mapAula(Aula aula) {
        if ( aula == null ) {
            return null;
        }

        AulaDTO aulaDTO = new AulaDTO();

        aulaDTO.setId( aula.getId() );
        aulaDTO.setNroAula( aula.getNroAula() );
        aulaDTO.setMedidas( aula.getMedidas() );
        aulaDTO.setCantidadPupitres( aula.getCantidadPupitres() );
        aulaDTO.setPizarron( aula.getPizarron() );

        return aulaDTO;
    }
}
