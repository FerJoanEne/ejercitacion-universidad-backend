package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PabellonRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {


    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> findPabellonByDireccion_localidad(String localidad) {
        return repository.findPabellonByDireccion_localidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> findPabellonByNombreIgnoreCase(String nombre) {
        return repository.findPabellonByNombreIgnoreCase(nombre);
    }
}
