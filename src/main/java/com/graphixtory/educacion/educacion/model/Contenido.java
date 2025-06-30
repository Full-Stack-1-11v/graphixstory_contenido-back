package com.graphixtory.educacion.educacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "nivel_educativo", nullable = false)
    private String nivelEducativo;

    @Column (name = "materia", nullable = false)
    private String materia;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    public Contenido(Long id, String nombre, String nivelEducativo, String materia, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.nivelEducativo = nivelEducativo;
        this.materia = materia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Contenido() {
    }
}