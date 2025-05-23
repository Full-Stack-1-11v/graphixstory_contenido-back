package com.graphixtory.educacion.educacion.service;

import com.graphixtory.educacion.educacion.model.Curso;
import com.graphixtory.educacion.educacion.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository; // Hazlo final

    // Inyección por constructor
    public CursoService(CursoRepository cursoRepository) { // Spring Boot 2.x+ @Autowired es opcional si solo hay un constructor
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> findAll(){
        return cursoRepository.findAll();
    }

    public Curso findById(long id){
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso save(Curso curso){
        return cursoRepository.save(curso);
    }

    public void delete(long id){
        cursoRepository.deleteById(id);
    }

    public List<Curso> buscarPorNombre(String nombre) {
        return cursoRepository.findByNombre(nombre);
    }

    public List<Curso> buscarPorNivelEducativo(Integer nivelEducativo) {
        return cursoRepository.findByNivelEducativo(nivelEducativo);
    }

    public List<Curso> buscarPorMateria(String materia) {
        return cursoRepository.findByMateria(materia); //
    }

    public List<Curso> buscarPorCuposDisponibles(Integer cuposDisponibles) {
        return cursoRepository.findByCuposDisponibles(cuposDisponibles);
    }

    public List<Curso> buscarPorCosto(Integer costo) {
        return cursoRepository.findByCosto(costo); //
    }

    public List<Curso> buscarPorFechaInicio(Date fechaInicio) {
        return cursoRepository.findByFechaInicio(fechaInicio);
    }

    public List<Curso> buscarPorFechaFin(Date fechaFin) {
        return cursoRepository.findByFechaFin(fechaFin);
    }
}
