package com.graphixtory.educacion.educacion.service;

import com.graphixtory.educacion.educacion.model.MaterialEducativo;
import com.graphixtory.educacion.educacion.repository.MaterialEducativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Date;

@Service
public class MaterialEducativoService {

    private final MaterialEducativoRepository materialEducativoRepository;


    @Autowired
    public MaterialEducativoService(MaterialEducativoRepository materialEducativoRepository) {
        this.materialEducativoRepository = materialEducativoRepository;
    }

    public List<MaterialEducativo> findAll() {
        return materialEducativoRepository.findAll();
    }

    public MaterialEducativo save(MaterialEducativo materialEducativo) {
        return materialEducativoRepository.save(materialEducativo);
    }

    public MaterialEducativo findById(Long id) {
        return materialEducativoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        materialEducativoRepository.deleteById(id);
    }

    public List<MaterialEducativo> buscarPorTituloMaterial(String titulo_material) {
        return materialEducativoRepository.findByTituloMaterial(titulo_material);
    }

    public List<MaterialEducativo> buscarPorFormatoContenido(String formato_contenido) {
        return materialEducativoRepository.findByFormatoContenido(formato_contenido);
    }

    public List<MaterialEducativo> buscarPorTipoRecurso(String tipo_recurso) {
        return materialEducativoRepository.findByTipoRecurso(tipo_recurso);
    }

    public List<MaterialEducativo> buscarPorUrlAccesoRecurso(String url_acceso_recurso) {
        return materialEducativoRepository.findByUrlAccesoRecurso(url_acceso_recurso);
    }

    public List<MaterialEducativo> buscarPorResumenDidactico(String resumen_didactico) {
        return materialEducativoRepository.findByResumenDidactico(resumen_didactico);
    }

    public List<MaterialEducativo> buscarPorEstadoPublicacion(String estado_publicacion) {
        return materialEducativoRepository.findByEstadoPublicacion(estado_publicacion);
    }

    public List<MaterialEducativo> buscarPorEstadoMaterial(String estado_material) {
        return materialEducativoRepository.findByEstadoMaterial(estado_material);
    }

    public List<MaterialEducativo> buscarPorFechaCreacion(Date fecha_creacion) {
        return materialEducativoRepository.findByFechaCreacion(fecha_creacion);
    }

    public List<MaterialEducativo> buscarPorFechaUltimaRevision(Date fecha_ultima_revision) {
        return materialEducativoRepository.findByFechaUltimaRevision(fecha_ultima_revision);
    }

    public List<MaterialEducativo> buscarPorCursoId(Long curso_id) {
        return materialEducativoRepository.findByCurso_id(curso_id);
    }

}
