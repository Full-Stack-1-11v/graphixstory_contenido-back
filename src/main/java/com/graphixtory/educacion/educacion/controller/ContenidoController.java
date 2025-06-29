package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.service.ContenidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Contenido> crear(@RequestBody Contenido c) {
        Contenido nuevoContenido = servicio.crearContenido(c);
        return new ResponseEntity<>(nuevoContenido, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los contenidos", description = "Lista todos los contenidos")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public ResponseEntity<List<Contenido>> listar() {
        List<Contenido> contenidos = servicio.listarContenidos();
        return new ResponseEntity<>(contenidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene contenido por ID", description = "Busca contenido por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public ResponseEntity<Contenido> obtener(@PathVariable Long id) {
        Optional<Contenido> contenido = servicio.obtenerPorId(id);
        return contenido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza contenido", description = "Modifica un contenido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido actualizado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public ResponseEntity<Contenido> actualizar(@PathVariable Long id, @RequestBody Contenido c) {
        Contenido contenidoActualizado = servicio.actualizarContenido(id, c);
        if (contenidoActualizado != null) {
            return new ResponseEntity<>(contenidoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina contenido", description = "Borra un contenido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contenido eliminado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminarContenido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    @Operation(summary = "Busca por nombre", description = "Encuentra contenidos por nombre")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public ResponseEntity<List<Contenido>> buscarPorNombre(@PathVariable String nombre) {
        List<Contenido> contenidos = servicio.buscarPorNombre(nombre);
        return new ResponseEntity<>(contenidos, HttpStatus.OK);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    @Operation(summary = "Busca por nivel educativo", description = "Encuentra contenidos por nivel")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public ResponseEntity<List<Contenido>> buscarPorNivelEducativo(@PathVariable String nivelEducativo) {
        List<Contenido> contenidos = servicio.buscarPorNivelEducativo(nivelEducativo);
        return new ResponseEntity<>(contenidos, HttpStatus.OK);
    }

    @GetMapping("/buscar/materia/{materia}")
    @Operation(summary = "Busca por materia", description = "Encuentra contenidos por materia")
    @ApiResponse(responseCode = "200", description = "Éxito")
    public ResponseEntity<List<Contenido>> buscarPorMateria(@PathVariable String materia) {
        List<Contenido> contenidos = servicio.buscarPorMateria(materia);
        return new ResponseEntity<>(contenidos, HttpStatus.OK);
    }

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    @Operation(summary = "Busca por fecha de inicio", description = "Encuentra contenidos que inician en la fecha (YYYY-MM-DD)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "400", description = "Fecha inválida")
    })
    public ResponseEntity<List<Contenido>> buscarPorFechaInicio(@PathVariable String fechaStr) {
        try {
            LocalDate fechaSolo = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDateTime fecha = fechaSolo.atStartOfDay();
            List<Contenido> contenidos = servicio.buscarPorFechaInicio(fecha);
            return new ResponseEntity<>(contenidos, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar/fechaFin/{fechaStr}")
    @Operation(summary = "Busca por fecha de fin", description = "Encuentra contenidos que terminan en la fecha (YYYY-MM-DD)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "400", description = "Fecha inválida")
    })
    public ResponseEntity<List<Contenido>> buscarPorFechaFin(@PathVariable String fechaStr) {
        try {
            LocalDate fechaSolo = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDateTime fecha = fechaSolo.atTime(23, 59, 59);
            List<Contenido> contenidos = servicio.buscarPorFechaFin(fecha);
            return new ResponseEntity<>(contenidos, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}