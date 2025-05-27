package com.graphixtory.educacion.educacion.repository;

import com.graphixtory.educacion.educacion.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long>{

    @Query("SELECT c FROM Curso c WHERE c.nombre = :nombre")
    List<Curso> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Curso c WHERE c.nivel_educativo = :nivelEducativo")
    List<Curso> findByNivelEducativo(@Param("nivelEducativo") Integer nivel_educativo);

    @Query("SELECT c FROM Curso c WHERE c.materia = :materia")
    List<Curso> findByMateria(@Param("materia") String materia);

    @Query("SELECT c FROM Curso c WHERE c.cupos_disponibles = :cuposDisponibles")
    List<Curso> findByCuposDisponibles(@Param("cuposDisponibles")Integer cupos_disponibles);

    @Query("SELECT c FROM Curso c WHERE c.costo = :costo")
    List<Curso> findByCosto(@Param("costo") Integer costo);

    @Query("SELECT c FROM Curso c WHERE c.fecha_inicio = :fechaInicio")
    List<Curso> findByFechaInicio(@Param("fechaInicio") Date fecha_inicio);

    @Query("SELECT c FROM Curso c WHERE c.fecha_fin = :fechaFin")
    List<Curso> findByFechaFin(@Param("fechaFin") Date fecha_fin);
}
