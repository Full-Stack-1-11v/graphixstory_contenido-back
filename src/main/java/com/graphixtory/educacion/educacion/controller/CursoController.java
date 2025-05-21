package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Curso;
import com.graphixtory.educacion.educacion.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/curso")
public class CursoController {

    @Autowired

    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar(){

        List<Curso> curso = cursoService.findAll();
        if (curso.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(curso);
    }
}
