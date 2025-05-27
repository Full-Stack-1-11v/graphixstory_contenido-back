package com.graphixtory.educacion.educacion.service;

import com.graphixtory.educacion.educacion.model.Curso;
import com.graphixtory.educacion.educacion.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service

public class CursoService {

    @Autowired
    private CursoRepository repo;

    public Curso crearCurso(Curso curso){
        return repo.save(curso);
    }

    public List<Curso> listarCursos(){
        return repo.findAll();
    }

    public Optional<Curso> obtenerPorId(long id){
        return repo.findById(id);
    }

    public Curso actualizarCurso(Long id, Curso nuevoCurso) {
        return repo.findById(id).map(cursoExistente -> {
            cursoExistente.setNombre(nuevoCurso.getNombre());
            cursoExistente.setDescripcion(nuevoCurso.getDescripcion());
            cursoExistente.setNivel_educativo(nuevoCurso.getNivel_educativo());
            cursoExistente.setMateria(nuevoCurso.getMateria());
            cursoExistente.setCupos_disponibles(nuevoCurso.getCupos_disponibles());
            cursoExistente.setCosto(nuevoCurso.getCosto());
            cursoExistente.setFecha_inicio(nuevoCurso.getFecha_inicio());
            cursoExistente.setFecha_fin(nuevoCurso.getFecha_fin());
            return repo.save(cursoExistente);
        }).orElse(null);
    }

    public void eliminarCurso(long id){
        repo.deleteById(id);
    }

    public List<Curso> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public List<Curso> buscarPorDescripcion(String descripcion) {
        return repo.findByDescripcion(descripcion);
    }

    public List<Curso> buscarPorNivelEducativo(Integer nivelEducativo) {
        return repo.findByNivelEducativo(nivelEducativo);
    }

    public List<Curso> buscarPorMateria(String materia) {
        return repo.findByMateria(materia);
    }

    public List<Curso> buscarPorCuposDisponibles(Integer cuposDisponibles){ return repo.findByCuposDisponibles(cuposDisponibles);}

    public List<Curso> buscarPorCosto(Integer costo) { return repo.findByCosto(costo);}

    public List<Curso> buscarPorFechaInicio(Date fechaInicio) {
        return repo.findByFechaInicio(fechaInicio);
    }

    public List<Curso> buscarPorFechaFin(Date fechaFin) {
        return repo.findByFechaFin(fechaFin);
    }
}
