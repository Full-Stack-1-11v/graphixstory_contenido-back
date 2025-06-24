package com.graphixtory.educacion.educacion.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.graphixtory.educacion.educacion.model.Contenido;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.graphixtory.educacion.educacion.service.ContenidoService;

@WebMvcTest(ContenidoController.class)
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContenidoService contenidoService;

    @Test
    void crearContenido_debeRetornarContenidoCreado() throws Exception{
        Contenido contenido = new Contenido();
        contenido.setId(2L);
        contenido.setNombre("Matemáticas Avanzadas");
        contenido.setNivelEducativo("Superior");
        contenido.setMateria("Algebra");
        contenido.setFechaInicio(LocalDateTime.of(2025, 7, 1, 0, 0));
        contenido.setFechaFin(LocalDateTime.of(2025, 12, 31, 23, 59,59));

        Mockito.when(contenidoService.crearContenido(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(post("/api/contenidos")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"nombre\":\"Matemáticas Aplicada\",\"nivelEducativo\":\"Superior\",\"materia\":\"Python\",\"fechaInicio\":\"2025-01-10T09:00:00\",\"fechaFin\":\"2025-12-15T18:00:00\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(2L))
        .andExpect(jsonPath("$.nombre").value("Matemáticas Aplicada"));
    }

    @Test
    void listarContenidos_debeRetornarListaVacia() throws Exception {
        Mockito.when(contenidoService.listarContenidos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/contenidos"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void listarContenidos_debeRetornarListaContenidos() throws Exception {

        Contenido c1 = new Contenido();
        c1.setId(1L);
        c1.setNombre("Formación Ciudadana");
        c1.setNivelEducativo("Básico");
        c1.setMateria("Historia");
        c1.setFechaInicio(LocalDateTime.of(2025, 3, 1, 9, 0));
        c1.setFechaFin(LocalDateTime.of(2025, 6, 30, 17, 0));

        Contenido c2 = new Contenido();
        c2.setId(2L);
        c2.setNombre("Fundamentos de Programación");
        c2.setNivelEducativo("Superior");
        c2.setMateria("Python");
        c2.setFechaInicio(LocalDateTime.of(2025, 8, 1, 10, 0));
        c2.setFechaFin(LocalDateTime.of(2025, 11, 30, 16, 0));

        Mockito.when(contenidoService.listarContenidos()).thenReturn(Arrays.asList(c1, c2));

        mockMvc.perform(get("/api/contenidos"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].nombre").value("Formación Ciudadana"))
        .andExpect(jsonPath("$[1].nombre").value("Fundamentos de Programación"));
    }

    @Test
    void obtenerContenidoPorId_debeRetornarContenidos() throws Exception{
        Contenido contenido = new Contenido();
        contenido.setId(1L);
        contenido.setNombre("Programación Java");

        Mockito.when(contenidoService.obtenerPorId(1L)).thenReturn(Optional.of(contenido));

        mockMvc.perform(get("/api/contenidos/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.nombre", is("Programación Java")));
    }

    @Test
    void actualizarContenido_debeRetornarContenidoActualizado() throws Exception {
        Contenido contenido = new Contenido();
        contenido.setId(1L);
        contenido.setNombre("Matemática Aplicada");
        contenido.setNivelEducativo("Superior");
        contenido.setFechaInicio(LocalDateTime.of(2025, 2, 1, 8, 0));
        contenido.setFechaFin(LocalDateTime.of(2025, 7, 31, 19, 0));

        Mockito.when(contenidoService.actualizarContenido(eq(1L), any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(put("/api/contenidos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Matemática Aplicada\",\"nivelEducativo\":\"Superior\",\"fechaInicio\":\"2025-02-01T08:00:00\",\"fechaFin\":\"2025-07-31T19:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Matemática Aplicada")));
    }

    @Test
    void eliminarContenido_debeRetornarNoContent() throws Exception {
        Mockito.doNothing().when(contenidoService).eliminarContenido(1L);

        mockMvc.perform(delete("/api/contenidos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarPorNombre_debeRetornarListaFiltrada() throws Exception{
         Contenido c1 = new Contenido();
        c1.setId(1L);
        c1.setNombre("Desarrollo Web");
        Contenido c2 = new Contenido();
        c2.setId(2L);
        c2.setNombre("Programación Avanzada");

        Mockito.when(contenidoService.buscarPorNombre("Desarrollo Web")).thenReturn(Collections.singletonList(c1));

        mockMvc.perform(get("/api/contenidos/buscar/nombre/Desarrollo Web"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].nombre", is("Desarrollo Web")));
    }

    @Test
    void buscarPorFechaInicio_debeRetornarListaFiltrada() throws Exception {
        Contenido contenido = new Contenido();
        contenido.setId(1L);
        contenido.setNombre("Base de Datos");
        contenido.setFechaInicio(LocalDateTime.of(2024, 7, 1, 0, 0));

        Mockito.when(contenidoService.buscarPorFechaInicio(eq(LocalDate.parse("2024-07-01").atStartOfDay()))).thenReturn(Collections.singletonList(contenido));

        mockMvc.perform(get("/api/contenidos/buscar/fechaInicio/2024-07-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Base de Datos")));
    }

    @Test
    void buscarPorFechaInicio_fechaInvalidaDebeRetornarVacio() throws Exception {
        
        mockMvc.perform(get("/api/contenidos/buscar/fechaInicio/fecha-invalida"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void buscarPorFechaFin_debeRetornarListaFiltrada() throws Exception {
        Contenido c1 = new Contenido();
        c1.setId(1L);
        c1.setNombre("Biología Marina");
        c1.setFechaInicio(LocalDateTime.of(2025, 6, 1, 9, 0));
        c1.setFechaFin(LocalDateTime.of(2025, 7, 31, 23, 59, 59));

        Mockito.when(contenidoService.buscarPorFechaFin(eq(LocalDate.parse("2025-07-31").atTime(23, 59, 59)))).thenReturn(Collections.singletonList(c1));

        mockMvc.perform(get("/api/contenidos/buscar/fechaFin/2025-07-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Biología Marina")));
}

    @Test
    void buscarPorFechaFin_fechaInvalidaDebeRetornarVacio() throws Exception {

        mockMvc.perform(get("/api/contenidos/buscar/fechaFin/invalid-date"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}