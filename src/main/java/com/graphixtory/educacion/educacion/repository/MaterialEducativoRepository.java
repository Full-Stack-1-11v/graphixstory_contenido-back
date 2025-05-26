package com.graphixtory.educacion.educacion.repository;


import com.graphixtory.educacion.educacion.model.MaterialEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Date;

public interface MaterialEducativoRepository extends JpaRepository<MaterialEducativo, Long> {

    @Query("SELECT m FROM MaterialEducativo m WHERE m.curso.id =:curso_id")
    List<MaterialEducativo> findByCurso_id(@Param("curso_id") Long curso_id);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.tituloMaterial =:titulo_material")
    List<MaterialEducativo> findByTituloMaterial(@Param("titulo_material") String titulo_material);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.tipoRecurso =:tipo_recurso")
    List<MaterialEducativo> findByTipoRecurso(@Param("tipo_recurso") String tipo_recurso);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.estadoPublicacion =:estado_publicacion")
    List<MaterialEducativo> findByEstadoPublicacion(@Param("estado_publicacion") String estado_publicacion);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.estadoMaterial =:estado_material")
    List<MaterialEducativo> findByEstadoMaterial(@Param("estado_material") String estado_material);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.fechaCreacion =:fecha_creacion")
    List<MaterialEducativo> findByFechaCreacion(@Param("fecha_creacion") Date fecha_creacion);
}
