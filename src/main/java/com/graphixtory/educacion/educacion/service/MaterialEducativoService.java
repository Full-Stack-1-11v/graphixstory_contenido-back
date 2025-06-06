package com.graphixtory.educacion.educacion.service;

import com.graphixtory.educacion.educacion.model.MaterialEducativo;
import com.graphixtory.educacion.educacion.repository.MaterialEducativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class MaterialEducativoService {

    @Autowired
    private MaterialEducativoRepository repo;

    public MaterialEducativo crearMaterialEducativo(MaterialEducativo materialEducativo) {
        return repo.save(materialEducativo);
    }

    public List<MaterialEducativo> listarMaterialesEducativos() {
        return repo.findAll();
    }

    public Optional<MaterialEducativo> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public MaterialEducativo actualizarMaterialEducativo(Long id, MaterialEducativo nuevoMaterial) {
        return repo.findById(id).map(materialExistente -> {
            materialExistente.setTituloMaterial(nuevoMaterial.getTituloMaterial());
            materialExistente.setTipoRecurso(nuevoMaterial.getTipoRecurso());
            materialExistente.setEstadoPublicacion(nuevoMaterial.getEstadoPublicacion());
            materialExistente.setEstadoMaterial(nuevoMaterial.getEstadoMaterial());
            materialExistente.setFechaCreacion(nuevoMaterial.getFechaCreacion());
            materialExistente.setContenido(nuevoMaterial.getContenido());

            return repo.save(materialExistente);
        }).orElse(null);
    }

    public void eliminarMaterialEducativo(Long id) {
        repo.deleteById(id);
    }

    public List<MaterialEducativo> buscarPorTituloMaterial(String tituloMaterial) {
        return repo.findByTituloMaterial(tituloMaterial);
    }

    public List<MaterialEducativo> buscarPorTipoRecurso(String tipoRecurso) {
        return repo.findByTipoRecurso(tipoRecurso);
    }

    public List<MaterialEducativo> buscarPorEstadoPublicacion(String estadoPublicacion) {
        return repo.findByEstadoPublicacion(estadoPublicacion);
    }

    public List<MaterialEducativo> buscarPorEstadoMaterial(String estadoMaterial) {
        return repo.findByEstadoMaterial(estadoMaterial);
    }

    public List<MaterialEducativo> buscarPorFechaCreacion(Date fechaCreacion) {
        return repo.findByFechaCreacion(fechaCreacion);
    }


    public List<MaterialEducativo> buscarPorContenidoId(Long contenidoId) {
        return repo.findByContenido_id(contenidoId); // Esto mapeará a `material.curso.id` en JPA
    }

}
