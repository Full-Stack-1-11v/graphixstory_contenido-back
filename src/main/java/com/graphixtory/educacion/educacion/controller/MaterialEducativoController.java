package com.graphixtory.educacion.educacion.controller;

import com.graphixtory.educacion.educacion.model.MaterialEducativo;
import com.graphixtory.educacion.educacion.service.MaterialEducativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/materiales-educativos")
public class MaterialEducativoController {

    @Autowired
    private MaterialEducativoService servicio;

    @PostMapping
    public MaterialEducativo crear(@RequestBody MaterialEducativo m) {
        return servicio.crearMaterialEducativo(m);
    }

    @GetMapping
    public List<MaterialEducativo> listar() {
        return servicio.listarMaterialesEducativos();
    }

    @GetMapping("/{id}")
    public MaterialEducativo obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public MaterialEducativo actualizar(@PathVariable Long id, @RequestBody MaterialEducativo m) {
        return servicio.actualizarMaterialEducativo(id, m);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarMaterialEducativo(id);
    }

    @GetMapping("/buscar/curso/{cursoId}")
    public List<MaterialEducativo> buscarPorCursoId(@PathVariable Long cursoId) {
        return servicio.buscarPorCursoId(cursoId);
    }

    @GetMapping("/buscar/titulo/{tituloMaterial}")
    public List<MaterialEducativo> buscarPorTituloMaterial(@PathVariable String tituloMaterial) {
        return servicio.buscarPorTituloMaterial(tituloMaterial);
    }

    @GetMapping("/buscar/tipo/{tipoRecurso}")
    public List<MaterialEducativo> buscarPorTipoRecurso(@PathVariable String tipoRecurso) {
        return servicio.buscarPorTipoRecurso(tipoRecurso);
    }

    @GetMapping("/buscar/estado-publicacion/{estadoPublicacion}")
    public List<MaterialEducativo> buscarPorEstadoPublicacion(@PathVariable String estadoPublicacion) {
        return servicio.buscarPorEstadoPublicacion(estadoPublicacion);
    }

    @GetMapping("/buscar/estado-material/{estadoMaterial}")
    public List<MaterialEducativo> buscarPorEstadoMaterial(@PathVariable String estadoMaterial) {
        return servicio.buscarPorEstadoMaterial(estadoMaterial);
    }

    @GetMapping("/buscar/fecha-creacion/{fechaStr}")
    public List<MaterialEducativo> buscarPorFechaCreacion(@PathVariable String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            return servicio.buscarPorFechaCreacion(fecha);
        } catch (ParseException e) {
            return List.of();
        }
    }
}

