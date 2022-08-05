package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import datos.DatosDummy;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonaRepositoryTest {

    @Autowired
    @Qualifier("repositorioAlumnos")
    PersonaRepository alumnoRepository;

    @Autowired
    @Qualifier("repositorioEmpleados")
    PersonaRepository empleadoRepository;

    @Autowired
    @Qualifier("repositorioProfesores")
    PersonaRepository profesorRepository;

    @Test
    void buscarPorNombreYApellido() {
        //given

        Persona save = empleadoRepository.save(DatosDummy.empleado01());

        //when
        Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(), DatosDummy.empleado01().getApellido());

        //then
        assertThat(expected.get()).isInstanceOf(Empleado.class);
        assertThat(expected.get()).isEqualTo(save);

    }

    @Test
    void buscarPorDni() {
        //given
        Persona save = profesorRepository.save(profesor01());

        //when
        Optional<Persona> expected = profesorRepository.buscarPorDni(profesor01().getDni());

        //then
        assertThat(expected.get()).isInstanceOf(Profesor.class);
        assertThat(expected.get()).isEqualTo(save);
        assertThat(expected.get().getDni()).isEqualTo(save.getDni());
    }


    @Test
    void buscarPersonaPorApellido() {

        //given

        Iterable<Persona> personas = alumnoRepository.saveAll(Arrays.asList(
                alumno01(),
                alumno02(),
                alumno03())
        );

        //when

        List<Persona> expected = (List<Persona>) alumnoRepository.buscarPersonaPorApellido(alumno01().getApellido());

        assertThat(expected.size() == 1).isTrue();

    }
}