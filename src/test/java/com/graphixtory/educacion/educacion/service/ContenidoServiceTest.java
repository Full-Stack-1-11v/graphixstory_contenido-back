package com.graphixtory.educacion.educacion.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.repository.ContenidoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class ContenidoServiceTest {

    @Autowired
    private ContenidoService contenidoService;

    @MockBean
    private ContenidoRepository contenidoRepository;

    @Test
    public void testListarContenidos(){
        when(contenidoRepository.findAll()).thenReturn(List.of(new Contenido(2L, "Matemática Avanzada", "Nivel Básico", "Álgebra", LocalDateTime.now(), LocalDateTime.now())));

        List<Contenido> contenidos = contenidoService.listarContenidos();

        assertNotNull(contenidos);
        assertEquals(1, contenidos.size());
    }

    @Test
    public void testObtenerPorId(){
        long id = 1;
        Contenido contenido = new Contenido(id, "Programación Java", "Nivel Superior", "Informatica", LocalDateTime.now(), LocalDateTime.now());

        when(contenidoRepository.findById(id)).thenReturn(Optional.of(contenido));

        Contenido found = contenidoService.obtenerPorId(id).orElse(null);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testCrearContenido(){

        Contenido contenido = new Contenido(2L, "Formación Ciudadana", "Nivel Básico", "Historia", LocalDateTime.now(), LocalDateTime.now());

        when(contenidoRepository.save(contenido)).thenReturn(contenido);

        Contenido saved = contenidoService.crearContenido(contenido);

        assertNotNull(saved);
        assertEquals("Formación Ciudadana", saved.getNombre());
    }
    @Test
    public void testEliminarContenido(){
        long id = 1;

        doNothing().when(contenidoRepository).deleteById(id);

        contenidoService.eliminarContenido(id);

        verify(contenidoRepository, times(1)).deleteById(id);

    }

}