package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProfesorComandos implements CommandLineRunner {

    @Autowired
    private ProfesorDAO profesorDAO;

    @Override
    public void run(String... args) throws Exception {

        /*Direccion direccion2 = new Direccion("calle2","123","1616","","","localidad1");
        Direccion direccion3 = new Direccion("calle3","123","1616","","","localidad1");
        Persona profesor2 = new Profesor(null, "name_profesor2","last_name_profesor2","2222233333", direccion2, new BigDecimal(20000));
        Persona profesor3 = new Profesor(null, "name_profesor3","last_name_profesor3","3333344444", direccion3, new BigDecimal(30000));

        personaDAO.save(profesor2);
        personaDAO.save(profesor3);*/

        //asinar carrera al profesor con id 5
        /*Iterable<Carrera> ingSistemas = carreraDAO.findCarrerasByNombreContains("Sistemas");
        Optional<Persona> profesor = personaDAO.findById(5);

        Set<Carrera> carreras = new HashSet<Carrera>();
        ingSistemas.forEach(carreras::add);

        Profesor p = (Profesor) profesor.get();
        p.setCarreras(carreras);
        personaDAO.save(p);*/

        //prueba metodo findProfesoresByCarrera(nombre_carrera);
        /*Iterable<Persona> carrerasPorProfesor = profesorDAO.findProfesoresByCarrera("Ingenieria en Sistemas");
        carrerasPorProfesor.forEach(System.out::println);*/
    }
}
