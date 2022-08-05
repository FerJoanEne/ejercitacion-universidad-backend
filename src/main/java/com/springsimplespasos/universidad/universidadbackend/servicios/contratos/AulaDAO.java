package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;

import java.util.Optional;

public interface AulaDAO extends GenericoDAO<Aula>{

    Iterable<Aula> findAulasByPizarron(Pizarron pizarron);
    Iterable<Aula> findAulasByPabellon_Nombre(String nombrePabellon);
    Optional<Aula> findAulasByNroAula(Integer nroAula);

}
