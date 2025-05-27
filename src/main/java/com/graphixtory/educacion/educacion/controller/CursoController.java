package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Curso;
import com.graphixtory.educacion.educacion.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@RestController
@RequestMapping
public class CursoController {

    @Autowired
    private CursoService servicio;

    @PostMapping
    public Curso crear(@RequestBody Curso c) {
        return servicio.crearCurso(c);
    }

    @GetMapping
    public List<Curso> listar() {
        return servicio.listarCursos();
    }

    @GetMapping("/{id}")
    public Curso obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso c) {
        return servicio.actualizarCurso(id, c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarCurso(id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public List<Curso> buscarPorNombre(@PathVariable String nombre) {
        return servicio.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    public List<Curso> buscarPorNivelEducativo(@PathVariable Integer nivelEducativo) {
        return servicio.buscarPorNivelEducativo(nivelEducativo);
    }

    @GetMapping("/buscar/materia/{materia}")
    public List<Curso> buscarPorMateria(@PathVariable String materia) {
        return servicio.buscarPorMateria(materia);
    }

    @GetMapping("/buscar/costo/{cuposDisponibles}")
    public List<Curso> buscarPorCuposDisponibles(@PathVariable Integer cuposDisponibles) { return servicio.buscarPorCuposDisponibles(cuposDisponibles);}

    @GetMapping("/buscar/costo/{costo}")
    public List<Curso> buscarPorCosto(@PathVariable Integer costo){ return servicio.buscarPorCosto(costo);}

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    public List<Curso> buscarPorFechaInicio(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            return servicio.buscarPorFechaInicio(fecha);
        } catch (ParseException e) {
            return List.of();
        }
    }

    @GetMapping("/buscar/fechaFin/{fechaStr}")
    public List<Curso> buscarPorFechaFin(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            return servicio.buscarPorFechaFin(fecha);
        } catch (ParseException e) {
            return List.of();
        }
    }

}
