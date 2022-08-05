package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {

    Iterable<Pabellon> findPabellonByDireccion_localidad(String localidad);
    Iterable<Pabellon> findPabellonByNombreIgnoreCase(String nombre);

}
