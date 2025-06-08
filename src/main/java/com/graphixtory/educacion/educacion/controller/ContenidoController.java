package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService servicio;

    @PostMapping
    public Contenido crear(@RequestBody Contenido c) {
        return servicio.crearContenido(c);
    }

    @GetMapping
    public List<Contenido> listar() {
        return servicio.listarContenidos();
    }

    @GetMapping("/{id}")
    public Contenido obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Contenido actualizar(@PathVariable Long id, @RequestBody Contenido c) {
        return servicio.actualizarContenido(id, c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarContenido(id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public List<Contenido> buscarPorNombre(@PathVariable String nombre) {
        return servicio.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/nivelEducativo/{nivelEducativo}")
    public List<Contenido> buscarPorNivelEducativo(@PathVariable String nivelEducativo) {
        return servicio.buscarPorNivelEducativo(nivelEducativo);
    }

    @GetMapping("/buscar/materia/{materia}")
    public List<Contenido> buscarPorMateria(@PathVariable String materia) {
        return servicio.buscarPorMateria(materia);
    }

    @GetMapping("/buscar/fechaInicio/{fechaStr}")
    public List<Contenido> buscarPorFechaInicio(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            return servicio.buscarPorFechaInicio(fecha);
        } catch (ParseException e) {
            return List.of();
        }
    }

    @GetMapping("/buscar/fechaFin/{fechaStr}")
    public List<Contenido> buscarPorFechaFin(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            return servicio.buscarPorFechaFin(fecha);
        } catch (ParseException e) {
            return List.of();
        }
    }

}
