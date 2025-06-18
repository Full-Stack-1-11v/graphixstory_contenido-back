package com.graphixtory.educacion.educacion.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphixtory.educacion.educacion.assemblers.ContenidoModelAssembler;
import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.service.ContenidoService;

@RestController
@RequestMapping("/v2/contenidos")
public class ContenidoControllerV2 {

    @Autowired
    private ContenidoService servicio;

    @Autowired
    private ContenidoModelAssembler assembler;

    @GetMapping
    public ResponseEntity<List<EntityModel<Contenido>>> listar() {
        List<EntityModel<Contenido>> contenidos = servicio.listarContenidos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Contenido>> obtener(@PathVariable Long id) {
        Contenido contenido = servicio.obtenerPorId(id).orElse(null);
        if (contenido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        EntityModel<Contenido> contenidoModel = assembler.toModel(contenido);
        return ResponseEntity.ok(contenidoModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Contenido>> crear(@RequestBody Contenido c) {
        Contenido nuevoContenido = servicio.crearContenido(c);
        EntityModel<Contenido> contenidoModel = assembler.toModel(nuevoContenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(contenidoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizar(@PathVariable Long id, @RequestBody Contenido c) {
        Contenido contenidoActualizado = servicio.actualizarContenido(id, c);
        if (contenidoActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contenidoActualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Contenido> actualizarParcial(@PathVariable Long id, @RequestBody Contenido c) {
        Contenido contenidoActualizado = servicio.patchContenido(id, c);
        if (contenidoActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contenidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicio.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        servicio.eliminarContenido(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Contenido>> buscarPorNombre(@PathVariable String nombre) {
        List<Contenido> contenidos = servicio.buscarPorNombre(nombre);
        if (contenidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    public ResponseEntity<List<Contenido>> buscarPorNivelEducativo(@PathVariable String nivelEducativo) {
        List<Contenido> contenidos = servicio.buscarPorNivelEducativo(nivelEducativo);
        if (contenidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/buscar/materia/{materia}")
    public ResponseEntity<List<Contenido>> buscarPorMateria(@PathVariable String materia) {
        List<Contenido> contenidos = servicio.buscarPorMateria(materia);
        if (contenidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    public ResponseEntity<List<Contenido>> buscarPorFechaInicio(@PathVariable String fechaStr) {
        try {
            LocalDate fechaSolo = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDateTime fecha = fechaSolo.atStartOfDay();
            List<Contenido> contenidos = servicio.buscarPorFechaInicio(fecha);
            if (contenidos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(contenidos);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/buscar/fechaFin/{fechaStr}")
    public ResponseEntity<List<Contenido>> buscarPorFechaFin(@PathVariable String fechaStr) {
        try {
            LocalDate fechaSolo = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDateTime fecha = fechaSolo.atTime(23, 59, 59);
            List<Contenido> contenidos = servicio.buscarPorFechaFin(fecha);
            if (contenidos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(contenidos);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
