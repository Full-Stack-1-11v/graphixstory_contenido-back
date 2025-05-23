package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Curso;
import com.graphixtory.educacion.educacion.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@RestController
@RequestMapping
public class CursoController {

    private final CursoService cursoService;


    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    public ResponseEntity<Curso> guardarCursos(@RequestBody Curso curso) {
        Curso savedCurso = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursos(@PathVariable Integer id) {
        try {
            Curso curso = cursoService.findById(id);
            if (curso == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(curso);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        try {
            cursoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Curso>> buscarCursosPorNombre(@PathVariable String nombre) {
        List<Curso> cursos = cursoService.buscarPorNombre(nombre);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    public ResponseEntity<List<Curso>> buscarCursosPorNivelEducativo(@PathVariable Integer nivelEducativo) {
        List<Curso> cursos = cursoService.buscarPorNivelEducativo(nivelEducativo);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar/materia/{materia}")
    public ResponseEntity<List<Curso>> buscarCursosPorMateria(@PathVariable String materia) {
        List<Curso> cursos = cursoService.buscarPorMateria(materia);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar/cuposDisponibles/{cupos}")
    public ResponseEntity<List<Curso>> buscarCursosPorCuposDisponibles(@PathVariable Integer cupos) {
        List<Curso> cursos = cursoService.buscarPorCuposDisponibles(cupos);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar/costo/{costo}")
    public ResponseEntity<List<Curso>> buscarCursosPorCosto(@PathVariable Integer costo) {
        List<Curso> cursos = cursoService.buscarPorCosto(costo);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    public ResponseEntity<List<Curso>> buscarCursosPorFechaInicio(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            List<Curso> cursos = cursoService.buscarPorFechaInicio(fecha);
            if (cursos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cursos);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar/fechaFin/{fechaStr}")
    public ResponseEntity<List<Curso>> buscarCursosPorFechaFin(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            List<Curso> cursos = cursoService.buscarPorFechaFin(fecha);
            if (cursos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cursos);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
