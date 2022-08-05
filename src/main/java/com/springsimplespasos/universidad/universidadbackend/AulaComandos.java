package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AulaComandos implements CommandLineRunner {

    @Autowired
    private AulaDAO aulaDAO;

    @Autowired
    private PabellonDAO pabellonDAO;


    @Override
    public void run(String... args) throws Exception {

        //Insert de datos de prueba
        /*Aula a1 = new Aula(null,1,"10x10",25, Pizarron.PIZARRA_TIZA);
        Aula a2 = new Aula(null,2,"15x15",35, Pizarron.PIZARRA_TIZA);
        Aula a3 = new Aula(null,3,"10x15",30, Pizarron.PIZARRA_BLANCA);
        Aula a4 = new Aula(null,4,"20x20",40, Pizarron.PIZARRA_BLANCA);

        aulaDAO.save(a1);
        aulaDAO.save(a2);
        aulaDAO.save(a3);
        aulaDAO.save(a4);*/


        /*Optional<Pabellon> pabellon = pabellonDAO.findById(1);
        Pabellon pab = pabellon.get();

        Optional<Aula> aula = aulaDAO.findById(1);
        Aula a1 = aula.get();
        a1.setPabellon(pab);
        aulaDAO.save(a1);*/

        /*Iterable<Aula> aulas = aulaDAO.findAulasByPabellon_Nombre("Desarrollo Humano");
        aulas.forEach(System.out::println);*/

    }
}
