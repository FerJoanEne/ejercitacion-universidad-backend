package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PabellonComandos implements CommandLineRunner {

    @Autowired
    private PabellonDAO pabellonDAO;

    @Override
    public void run(String... args) throws Exception {

        //insert de pabellones
        /*Direccion direccion1 = new Direccion("calle_pabellon1","2525","1665","","","Tortuguitas");
        Pabellon p1 = new Pabellon(null, 25.0, "Industria", direccion1);

        Direccion direccion2 = new Direccion("calle_pabellon2","2345","1365","","","Pacheco");
        Pabellon p2 = new Pabellon(null, 25.0, "Desarrollo Humano", direccion2);

        Pabellon pSave1 = pabellonDAO.save(p1);
        Pabellon pSave2 = pabellonDAO.save(p2);

        System.out.println(pSave1);
        System.out.println(pSave2);*/

        /*Iterable<Pabellon> pabellones = pabellonDAO.findPabellonByNombreIgnoreCase("Desarrollo Humano");
        pabellones.forEach(System.out::println);

        Iterable<Pabellon> pabellones2 = pabellonDAO.findPabellonByDireccion_localidad("Tortuguitas");
        pabellones2.forEach(System.out::println);*/

    }
}
