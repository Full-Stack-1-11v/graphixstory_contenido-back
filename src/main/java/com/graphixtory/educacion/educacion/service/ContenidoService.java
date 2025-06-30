package com.graphixtory.educacion.educacion.service;

import com.graphixtory.educacion.educacion.model.Contenido;
import com.graphixtory.educacion.educacion.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository repo;

    public List<Contenido> listarContenidos(){
        return repo.findAll();
    }

    public Optional<Contenido> obtenerPorId(long id){
        return repo.findById(id);
    }

    public Contenido crearContenido(Contenido contenido){
        return repo.save(contenido);
    }

    public void eliminarContenido(long id){
        repo.deleteById(id);
    }

    public Contenido actualizarContenido(Long id, Contenido nuevoContenido) {
        if (repo.existsById(id)) {
            nuevoContenido.setId(id);
            return repo.save(nuevoContenido);
        }
        return null;
    }

    public Contenido patchContenido(Long id, Contenido cambiosParciales) {
        Optional<Contenido> optionalContenido = repo.findById(id);

        if (optionalContenido.isEmpty()) {
            return null;
        }

        Contenido contenidoExistente = optionalContenido.get();

        if (cambiosParciales.getNombre() != null)
            contenidoExistente.setNombre(cambiosParciales.getNombre());
        if (cambiosParciales.getNivelEducativo() != null)
            contenidoExistente.setNivelEducativo(cambiosParciales.getNivelEducativo());
        if (cambiosParciales.getMateria() != null)
            contenidoExistente.setMateria(cambiosParciales.getMateria());
        if (cambiosParciales.getFechaInicio() != null)
            contenidoExistente.setFechaInicio(cambiosParciales.getFechaInicio());
        if (cambiosParciales.getFechaFin() != null)
            contenidoExistente.setFechaFin(cambiosParciales.getFechaFin());

        return repo.save(contenidoExistente);
    }

    public List<Contenido> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public List<Contenido> buscarPorNivelEducativo(String nivelEducativo) {
        return repo.findByNivelEducativo(nivelEducativo);
    }

    public List<Contenido> buscarPorMateria(String materia) {
        return repo.findByMateria(materia);
    }

    public List<Contenido> buscarPorFechaInicio(LocalDateTime fechaInicio) {
        return repo.findByFechaInicio(fechaInicio);
    }

    public List<Contenido> buscarPorFechaFin(LocalDateTime fechaFin) {
        return repo.findByFechaFin(fechaFin);
    }
}