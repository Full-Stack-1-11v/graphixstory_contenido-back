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

    @Test
    public void testActualizarContenido() {
        Long id = 1L;
        Contenido contenidoExistente = new Contenido(id, "Antiguo Nombre", "Antiguo Nivel", "Antigua Materia", LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1));
        Contenido contenidoActualizado = new Contenido(id, "Nuevo Nombre", "Nuevo Nivel", "Nueva Materia", LocalDateTime.now(), LocalDateTime.now());

        when(contenidoRepository.findById(id)).thenReturn(Optional.of(contenidoExistente));
        when(contenidoRepository.save(contenidoExistente)).thenReturn(contenidoActualizado);

        Contenido result = contenidoService.actualizarContenido(id, contenidoActualizado);

        assertNotNull(result);
        assertEquals("Nuevo Nombre", result.getNombre());
        assertEquals("Nuevo Nivel", result.getNivel_educativo());
        assertEquals("Nueva Materia", result.getMateria());
        verify(contenidoRepository, times(1)).findById(id);
        verify(contenidoRepository, times(1)).save(contenidoExistente);
    }

    @Test
    public void testBuscarPorNombre() {
        String nombreBuscado = "Matemática Avanzada";
        List<Contenido> contenidos = List.of(new Contenido(2L, "Matemática Avanzada", "Nivel Básico", "Álgebra", LocalDateTime.now(), LocalDateTime.now()));
        when(contenidoRepository.findByNombre(nombreBuscado)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorNombre(nombreBuscado);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(nombreBuscado, found.get(0).getNombre());
        verify(contenidoRepository, times(1)).findByNombre(nombreBuscado);
    }

    @Test
    public void testBuscarPorNivelEducativo() {
        String nivelBuscado = "Nivel Básico";
        List<Contenido> contenidos = List.of(new Contenido(2L, "Formación Ciudadana", "Nivel Básico", "Historia", LocalDateTime.now(), LocalDateTime.now()));
        when(contenidoRepository.findByNivelEducativo(nivelBuscado)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorNivelEducativo(nivelBuscado);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(nivelBuscado, found.get(0).getNivel_educativo());
        verify(contenidoRepository, times(1)).findByNivelEducativo(nivelBuscado);
    }

    @Test
    public void testBuscarPorMateria() {
        String materiaBuscada = "Informatica";
        List<Contenido> contenidos = List.of(new Contenido(1L, "Programación Java", "Nivel Superior", "Informatica", LocalDateTime.now(), LocalDateTime.now()));
        when(contenidoRepository.findByMateria(materiaBuscada)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorMateria(materiaBuscada);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(materiaBuscada, found.get(0).getMateria());
        verify(contenidoRepository, times(1)).findByMateria(materiaBuscada);
    }

    @Test
    public void testBuscarPorFechaInicio() {
        LocalDateTime fechaBuscada = LocalDateTime.now().minusDays(5);
        List<Contenido> contenidos = List.of(new Contenido(3L, "Taller de Pruebas", "Nivel Básico", "Software", fechaBuscada, fechaBuscada.plusDays(2)));
        when(contenidoRepository.findByFechaInicio(fechaBuscada)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorFechaInicio(fechaBuscada);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Taller de Pruebas", found.get(0).getNombre());
        assertEquals(fechaBuscada, found.get(0).getFecha_inicio());
        verify(contenidoRepository, times(1)).findByFechaInicio(fechaBuscada);
    }

    @Test
    public void testBuscarPorFechaFin() {
        LocalDateTime fechaBuscada = LocalDateTime.now().plusDays(10);
        List<Contenido> contenidos = List.of(new Contenido(4L, "Seminario Final", "Nivel Superior", "Investigación", fechaBuscada.minusDays(3), fechaBuscada));
        when(contenidoRepository.findByFechaFin(fechaBuscada)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorFechaFin(fechaBuscada);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Seminario Final", found.get(0).getNombre());
        assertEquals(fechaBuscada, found.get(0).getFecha_fin());
        verify(contenidoRepository, times(1)).findByFechaFin(fechaBuscada);
    }
}