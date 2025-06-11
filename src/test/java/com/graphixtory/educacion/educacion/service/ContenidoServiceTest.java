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
        String nombre = "Historia";
        List<Contenido> contenidos = List.of(new Contenido(1L, "Historia General", "Secundaria", "Historia", LocalDateTime.now(), LocalDateTime.now()));
        when(contenidoRepository.findByNombre(nombre)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorNombre(nombre);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Historia General", found.get(0).getNombre());
        verify(contenidoRepository, times(1)).findByNombre(nombre);
    }

    @Test
    public void testBuscarPorNivelEducativo() {
        String nivel = "Primaria";
        List<Contenido> contenidos = List.of(new Contenido(1L, "Matematicas", "Primaria", "Matematicas", LocalDateTime.now(), LocalDateTime.now()));
        when(contenidoRepository.findByNivelEducativo(nivel)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorNivelEducativo(nivel);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Primaria", found.get(0).getNivel_educativo());
        verify(contenidoRepository, times(1)).findByNivelEducativo(nivel);
    }

    @Test
    public void testBuscarPorMateria() {
        String materia = "Ciencias";
        List<Contenido> contenidos = List.of(new Contenido(1L, "Biologia", "Secundaria", "Ciencias", LocalDateTime.now(), LocalDateTime.now()));
        when(contenidoRepository.findByMateria(materia)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorMateria(materia);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Ciencias", found.get(0).getMateria());
        verify(contenidoRepository, times(1)).findByMateria(materia);
    }

    @Test
    public void testBuscarPorFechaInicio() {
        LocalDateTime fecha = LocalDateTime.now();
        List<Contenido> contenidos = List.of(new Contenido(1L, "Evento", "General", "Arte", fecha, fecha.plusDays(5)));
        when(contenidoRepository.findByFechaInicio(fecha)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorFechaInicio(fecha);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Evento", found.get(0).getNombre());
        verify(contenidoRepository, times(1)).findByFechaInicio(fecha);
    }

    @Test
    public void testBuscarPorFechaFin() {
        LocalDateTime fecha = LocalDateTime.now().plusDays(10);
        List<Contenido> contenidos = List.of(new Contenido(1L, "Conferencia", "Universidad", "Tecnologia", fecha.minusDays(5), fecha));
        when(contenidoRepository.findByFechaFin(fecha)).thenReturn(contenidos);

        List<Contenido> found = contenidoService.buscarPorFechaFin(fecha);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Conferencia", found.get(0).getNombre());
        verify(contenidoRepository, times(1)).findByFechaFin(fecha);
    }
}