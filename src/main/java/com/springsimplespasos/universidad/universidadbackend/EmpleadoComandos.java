package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PersonaRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EmpleadoComandos implements CommandLineRunner {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Override
    public void run(String... args) throws Exception {

        //insert de nuevos empleados sin pabellon asignado
        /*Direccion dir1 = new Direccion("calle_empleado1","453","1661","","","localidad_empleado1");
        Direccion dir2 = new Direccion("calle_empleado2","354","1616","","","localidad_empleado2");
        Direccion dir3 = new Direccion("calle_empleado3","444","1441","","","localidad_empleado3");
        Direccion dir4 = new Direccion("calle_empleado4","555","1336","","","localidad_empleado4");
        Empleado emp1 = new Empleado(null,"name_empleado1","lastname_empleado1","32165498", dir1,new BigDecimal(20000), TipoEmpleado.ADMINISTRATIVO);
        Empleado emp2 = new Empleado(null,"name_empleado2","lastname_empleado2","92165498", dir2,new BigDecimal(15000), TipoEmpleado.ADMINISTRATIVO);
        Empleado emp3 = new Empleado(null,"name_empleado3","lastname_empleado3","32167778", dir3,new BigDecimal(10000), TipoEmpleado.MANTENIMIENTO);
        Empleado emp4 = new Empleado(null,"name_empleado4","lastname_empleado4","99167778", dir4,new BigDecimal(9000), TipoEmpleado.MANTENIMIENTO);

        empleadoDAO.save(emp1);
        empleadoDAO.save(emp2);
        empleadoDAO.save(emp3);
        empleadoDAO.save(emp4);*/

        /*Iterable<Persona> empleados = empleadoDAO.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
        empleados.forEach(System.out::println);*/
    }
}
