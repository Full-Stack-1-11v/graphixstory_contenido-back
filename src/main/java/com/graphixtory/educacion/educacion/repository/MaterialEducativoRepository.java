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

    @Query("SELECT m FROM MaterialEducativo m WHERE m.formatoContenido =:formato_contenido")
    List<MaterialEducativo> findByFormatoContenido(@Param("formato_contenido") String formato_contenido);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.tipoRecurso =:tipo_recurso")
    List<MaterialEducativo> findByTipoRecurso(@Param("tipo_recurso") String tipo_recurso);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.urlAccesoRecurso =:url_acceso_recurso")
    List<MaterialEducativo> findByUrlAccesoRecurso(@Param("url_acceso_recurso") String url_acceso_recurso);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.resumenDidactico =:resumen_didactico")
    List<MaterialEducativo> findByResumenDidactico(@Param("resumen_didactico") String resumen_didactico);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.estadoPublicacion =:estado_publicacion")
    List<MaterialEducativo> findByEstadoPublicacion(@Param("estado_publicacion") String estado_publicacion);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.estadoMaterial =:estado_material")
    List<MaterialEducativo> findByEstadoMaterial(@Param("estado_material") String estado_material);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.fechaCreacion =:fecha_creacion")
    List<MaterialEducativo> findByFechaCreacion(@Param("fecha_creacion") Date fecha_creacion);

    @Query("SELECT m FROM MaterialEducativo m WHERE m.fechaUltimaRevision =:fecha_ultima_revision")
    List<MaterialEducativo> findByFechaUltimaRevision(@Param("fecha_ultima_revision") Date fecha_ultima_revision);
}
