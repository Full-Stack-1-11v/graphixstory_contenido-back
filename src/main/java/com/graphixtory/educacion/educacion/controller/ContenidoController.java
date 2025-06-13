package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.service.ContenidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/contenidos")
@Tag(name = "Contenidos", description = "Gestión de Contenidos Educativos")
public class ContenidoController {

    @Autowired
    private ContenidoService servicio;

    @PostMapping
    @Operation(summary = "Crea contenido", description = "Registra un nuevo contenido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contenido creado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Contenido crear(@RequestBody Contenido c) {
        return servicio.crearContenido(c);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los contenidos", description = "Lista todos los contenidos")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public List<Contenido> listar() {
        return servicio.listarContenidos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene contenido por ID", description = "Busca contenido por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public Contenido obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza contenido", description = "Modifica un contenido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido actualizado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public Contenido actualizar(@PathVariable Long id, @RequestBody Contenido c) {
        return servicio.actualizarContenido(id, c);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina contenido", description = "Borra un contenido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contenido eliminado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarContenido(id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    @Operation(summary = "Busca por nombre", description = "Encuentra contenidos por nombre")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public List<Contenido> buscarPorNombre(@PathVariable String nombre) {
        return servicio.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    @Operation(summary = "Busca por nivel educativo", description = "Encuentra contenidos por nivel")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public List<Contenido> buscarPorNivelEducativo(@PathVariable String nivelEducativo) {
        return servicio.buscarPorNivelEducativo(nivelEducativo);
    }

    @GetMapping("/buscar/materia/{materia}")
    @Operation(summary = "Busca por materia", description = "Encuentra contenidos por materia")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public List<Contenido> buscarPorMateria(@PathVariable String materia) {
        return servicio.buscarPorMateria(materia);
    }

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    @Operation(summary = "Busca por fecha de inicio", description = "Encuentra contenidos que inician en la fecha (YYYY-MM-DD)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "400", description = "Fecha inválida")
    })
    public List<Contenido> buscarPorFechaInicio(@PathVariable String fechaStr) {
        try {
            LocalDate fechaSolo = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDateTime fecha = fechaSolo.atStartOfDay();
            return servicio.buscarPorFechaInicio(fecha);
        } catch (DateTimeParseException e) {
            return List.of();
        }
    }

    @GetMapping("/buscar/fechaFin/{fechaStr}")
    @Operation(summary = "Busca por fecha de fin", description = "Encuentra contenidos que terminan en la fecha (YYYY-MM-DD)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "400", description = "Fecha inválida")
    })
    public List<Contenido> buscarPorFechaFin(@PathVariable String fechaStr) {
        try {
            LocalDate fechaSolo = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDateTime fecha = fechaSolo.atTime(23, 59, 59);
            return servicio.buscarPorFechaFin(fecha);
        } catch (DateTimeParseException e) {
            return List.of();
        }
    }
}