package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.repositorios.AulaRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{

    @Autowired
    public AulaDAOImpl(AulaRepository repository) {
        super(repository);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByPizarron(Pizarron pizarron) {
        return repository.findAulasByPizarron(pizarron);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByPabellon_Nombre(String nombrePabellon) {
        return repository.findAulasByPabellon_Nombre(nombrePabellon);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Aula> findAulasByNroAula(Integer nroAula) {
        return repository.findAulasByNroAula(nroAula);
    }
}
