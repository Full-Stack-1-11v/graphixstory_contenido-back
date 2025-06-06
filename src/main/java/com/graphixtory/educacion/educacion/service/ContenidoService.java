package com.graphixtory.educacion.educacion.service;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service

public class ContenidoService {

    @Autowired
    private ContenidoRepository repo;

    public Contenido crearContenido(Contenido contenido){
        return repo.save(contenido);
    }

    public List<Contenido> listarContenidos(){
        return repo.findAll();
    }

    public Optional<Contenido> obtenerPorId(long id){
        return repo.findById(id);
    }

    public Contenido actualizarContenido(Long id, Contenido nuevoContenido) {
        return repo.findById(id).map(contenidoExistente -> {
            contenidoExistente.setNombre(nuevoContenido.getNombre());
            contenidoExistente.setNivel_educativo(nuevoContenido.getNivel_educativo());
            contenidoExistente.setMateria(nuevoContenido.getMateria());
            contenidoExistente.setFecha_inicio(nuevoContenido.getFecha_inicio());
            contenidoExistente.setFecha_fin(nuevoContenido.getFecha_fin());
            return repo.save(contenidoExistente);
        }).orElse(null);
    }

    public void eliminarContenido(long id){
        repo.deleteById(id);
    }

    public List<Contenido> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public List<Contenido> buscarPorNivelEducativo(Integer nivelEducativo) {
        return repo.findByNivelEducativo(nivelEducativo);
    }
    public List<Contenido> buscarPorMateria(String materia) {
        return repo.findByMateria(materia);
    }

    public List<Contenido> buscarPorFechaInicio(Date fechaInicio) {
        return repo.findByFechaInicio(fechaInicio);
    }

    public List<Contenido> buscarPorFechaFin(Date fechaFin) {
        return repo.findByFechaFin(fechaFin);
    }
}
