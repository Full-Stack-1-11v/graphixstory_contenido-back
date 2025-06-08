package com.graphixtory.educacion.educacion.repository;

import com.graphixtory.educacion.educacion.model.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ContenidoRepository extends JpaRepository<Contenido, Long>{

    @Query("SELECT c FROM Contenido c WHERE c.nombre = :nombre")
    List<Contenido> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Contenido c WHERE c.nivel_educativo = :nivelEducativo")
    List<Contenido> findByNivelEducativo(@Param("nivelEducativo") String nivel_educativo);

    @Query("SELECT c FROM Contenido c WHERE c.materia = :materia")
    List<Contenido> findByMateria(@Param("materia") String materia);

    @Query("SELECT c FROM Contenido c WHERE c.fecha_inicio = :fechaInicio")
    List<Contenido> findByFechaInicio(@Param("fechaInicio") Date fecha_inicio);

    @Query("SELECT c FROM Contenido c WHERE c.fecha_fin = :fechaFin")
    List<Contenido> findByFechaFin(@Param("fechaFin") Date fecha_fin);
}
