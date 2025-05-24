package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.MaterialEducativo;
import com.graphixtory.educacion.educacion.service.MaterialEducativoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/materiales-educativos")
public class MaterialEducativoController {

    private final MaterialEducativoService materialEducativoService;

    // Inyección por constructor
    public MaterialEducativoController(MaterialEducativoService materialEducativoService) {
        this.materialEducativoService = materialEducativoService;
    }

    // 1. Listar todos los Materiales Educativos
    @GetMapping
    public ResponseEntity<List<MaterialEducativo>> listarMaterialesEducativos() {
        List<MaterialEducativo> materiales = materialEducativoService.findAll();
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    // 2. Guardar un nuevo Material Educativo
    @PostMapping
    public ResponseEntity<MaterialEducativo> guardarMaterialEducativo(@RequestBody MaterialEducativo materialEducativo) {
        MaterialEducativo savedMaterial = materialEducativoService.save(materialEducativo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMaterial);
    }

    // 3. Buscar Material Educativo por ID
    @GetMapping("/{id}")
    public ResponseEntity<MaterialEducativo> buscarMaterialEducativo(@PathVariable Long id) { // ID de MaterialEducativo es Long
        try {
            MaterialEducativo material = materialEducativoService.findById(id);
            if (material == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(material);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // O HttpStatus.INTERNAL_SERVER_ERROR
        }
    }

    // 4. Eliminar Material Educativo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaterialEducativo(@PathVariable Long id) {
        try {
            materialEducativoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/curso/{cursoId}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorCurso(@PathVariable Long cursoId) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorCursoId(cursoId);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/titulo/{tituloMaterial}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorTitulo(@PathVariable String tituloMaterial) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorTituloMaterial(tituloMaterial);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/formato/{formatoContenido}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorFormato(@PathVariable String formatoContenido) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorFormatoContenido(formatoContenido);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/tipo/{tipoRecurso}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorTipo(@PathVariable String tipoRecurso) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorTipoRecurso(tipoRecurso);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/url/{urlAccesoRecurso}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorUrlAcceso(@PathVariable String urlAccesoRecurso) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorUrlAccesoRecurso(urlAccesoRecurso);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/resumen/{resumenDidactico}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorResumen(@PathVariable String resumenDidactico) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorResumenDidactico(resumenDidactico);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/estado-publicacion/{estadoPublicacion}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorEstadoPublicacion(@PathVariable String estadoPublicacion) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorEstadoPublicacion(estadoPublicacion);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/estado-material/{estadoMaterial}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorEstadoMaterial(@PathVariable String estadoMaterial) {
        List<MaterialEducativo> materiales = materialEducativoService.buscarPorEstadoMaterial(estadoMaterial);
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/buscar/fecha-creacion/{fechaStr}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorFechaCreacion(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            List<MaterialEducativo> materiales = materialEducativoService.buscarPorFechaCreacion(fecha);
            if (materiales.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(materiales);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar/fecha-ultima-revision/{fechaStr}")
    public ResponseEntity<List<MaterialEducativo>> buscarMaterialesPorFechaUltimaRevision(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            List<MaterialEducativo> materiales = materialEducativoService.buscarPorFechaUltimaRevision(fecha);
            if (materiales.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(materiales);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build(); // Error si la fecha no tiene el formato correcto
        }
    }
}

