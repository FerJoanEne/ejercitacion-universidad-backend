package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import datos.DatosDummy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarreraRepositoryTest {

    @Autowired
    CarreraRepository carreraRepository;

    @BeforeEach
    void setUp(){
        carreraRepository.save(DatosDummy.carrera01());
        carreraRepository.save(DatosDummy.carrera02());
        carreraRepository.save(DatosDummy.carrera03());

    }

    @AfterEach
    void tearDown(){
        carreraRepository.deleteAll();
    }

    @Test
    @DisplayName("Buscar carreras por nombre")
    void findCarrerasByNombreContains() {
        //given

        //when
        Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContains("Sistemas");

        //then
        assertThat(((List<Carrera>)expected).size() == 2).isTrue();
    }

    @Test
    void findCarrerasByNombreContainsIgnoreCase() {
        //given

        //when
        Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContainsIgnoreCase("sistemas");

        //then
        assertThat(((List<Carrera>)expected).size() == 2).isTrue();
    }

    @Test
    @DisplayName("Carreras por cantidad anios after")
    void findCarrerasByCantidadAniosAfter() {
        //given

        //when
        Iterable<Carrera> expected = carreraRepository.findCarrerasByCantidadAniosAfter(3);

        //then
        assertThat(((List<Carrera>)expected).size() == 3).isTrue();
    }
}