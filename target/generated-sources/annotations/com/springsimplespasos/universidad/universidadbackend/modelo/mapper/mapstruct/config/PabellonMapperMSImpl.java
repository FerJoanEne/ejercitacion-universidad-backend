package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.config;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PabellonDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-31T21:05:42-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class PabellonMapperMSImpl implements PabellonMapperMS {

    @Override
    public PabellonDTO mapPabellon(Pabellon pabellon) {
        if ( pabellon == null ) {
            return null;
        }

        PabellonDTO pabellonDTO = new PabellonDTO();

        pabellonDTO.setId( pabellon.getId() );
        pabellonDTO.setMts2( pabellon.getMts2() );
        pabellonDTO.setNombre( pabellon.getNombre() );
        pabellonDTO.setDireccion( pabellon.getDireccion() );

        return pabellonDTO;
    }
}
