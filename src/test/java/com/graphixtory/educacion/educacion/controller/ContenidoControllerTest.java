package com.graphixtory.educacion.educacion.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;

import com.graphixtory.educacion.educacion.service.ContenidoService;

@WebMvcTest
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContenidoService contenidoService;

    @Test
    void listarContenidos_debeRetornarListaVacia() throws Exception {
        Mockito.when(contenidoService.listarContenidos()).thenReturn(Collections.emptyList());

        mockMvc.perform("/contenidos")
        .andExcept(status().isOk())
        .andExcept(jsonPath("$", hasSize(0)));
    }

    @Test
    void obtenerContenidos_noEncontrados() throws Exception{
        Mockito.when(contenidoService.obtenerPorId(2L)).thenReturn(Collections.emptyList());

        mockMvc.perform("/contenidos")
        .andExcept(status().isNotFound());
    }

}
