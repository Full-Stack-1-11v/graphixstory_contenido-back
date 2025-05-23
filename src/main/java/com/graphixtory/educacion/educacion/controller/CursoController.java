package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Curso;
import com.graphixtory.educacion.educacion.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.ok(curso);
        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }
}
