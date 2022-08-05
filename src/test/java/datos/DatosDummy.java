package datos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.*;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;

import java.math.BigDecimal;

public class DatosDummy {

    public static Carrera carrera01(){
        return new Carrera(null, "Ingenieria en Sistemas", 50, 5);
    }

    public static Carrera carrera02(){
        return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
    }

    public static Carrera carrera03(){
        return new Carrera(null, "Ingenieria Industrial", 60, 5);
    }

    public static Persona empleado01(){
        return new Empleado(null, "Lautaro", "Lopez", "13216548", new Direccion(), new BigDecimal("16658.10"), TipoEmpleado.ADMINISTRATIVO);
    }

    public static Persona empleado02(){
        return new Empleado(null, "Leandro", "Lopez", "13216548", new Direccion(), new BigDecimal("16658.10"), TipoEmpleado.MANTENIMIENTO);
    }

    public static Persona profesor01(){
        return new Profesor(null, "Martin", "Lugo", "123321654", new Direccion(), new BigDecimal("60000"));
    }

    public static Persona alumno01(){
        return new Alumno(null, "Jhon", "Benitez", "2103312", new Direccion());
    }

    public static Persona alumno02(){
        return new Alumno(null, "Andres", "Benez", "3103312", new Direccion());
    }

    public static Persona alumno03(){
        return new Alumno(null, "Joaquin", "Beniz", "4103312", new Direccion());
    }
}
