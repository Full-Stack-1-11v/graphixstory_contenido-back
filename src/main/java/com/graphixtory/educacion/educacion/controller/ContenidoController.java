package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.service.ContenidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/contenidos")
@Tag(name = "Contenidos", description = "Operaciones relacionadas con la gestión de Contenidos Educativos")
public class ContenidoController {

    @Autowired
    private ContenidoService servicio;

    @PostMapping
    @Operation(summary = "Crea un nuevo contenido educativo", description = "Permite registrar un nuevo objeto de contenido educativo en la base de datos")
    public Contenido crear(@RequestBody Contenido c) {
        return servicio.crearContenido(c);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los contenidos educativos", description = "Retorna una lista de todos los contenidos educativos registrados")
    public List<Contenido> listar() {
        return servicio.listarContenidos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un contenido educativo por ID", description = "Retorna un contenido educativo específico utilizando su ID")
    public Contenido obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un contenido educativo existente", description = "Permite modificar un contenido educativo en la base de datos a partir de su ID")
    public Contenido actualizar(@PathVariable Long id, @RequestBody Contenido c) {
        return servicio.actualizarContenido(id, c);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un contenido educativo", description = "Borra un contenido educativo de la base de datos usando su ID")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarContenido(id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    @Operation(summary = "Busca contenidos por nombre", description = "Retorna una lista de contenidos educativos cuyo nombre coincide con el parámetro de búsqueda")
    public List<Contenido> buscarPorNombre(@PathVariable String nombre) {
        return servicio.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    @Operation(summary = "Busca contenidos por nivel educativo", description = "Retorna una lista de contenidos educativos filtrados por nivel educativo")
    public List<Contenido> buscarPorNivelEducativo(@PathVariable String nivelEducativo) {
        return servicio.buscarPorNivelEducativo(nivelEducativo);
    }

    @GetMapping("/buscar/materia/{materia}")
    @Operation(summary = "Busca contenidos por materia", description = "Retorna una lista de contenidos educativos filtrados por materia")
    public List<Contenido> buscarPorMateria(@PathVariable String materia) {
        return servicio.buscarPorMateria(materia);
    }

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    @Operation(summary = "Busca contenidos por fecha de inicio", description = "Retorna una lista de contenidos educativos que inician en una fecha específica (formato YYYY-MM-DD)")
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
    @Operation(summary = "Busca contenidos por fecha de fin", description = "Retorna una lista de contenidos educativos que finalizan en una fecha específica (formato YYYY-MM-DD)")
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