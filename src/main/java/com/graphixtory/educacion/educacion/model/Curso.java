package com.graphixtory.educacion.educacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name= "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nivel_educativo", nullable = false)
    private Integer nivel_educativo;

    @Column(name = "materia", nullable = false)
    private String materia;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cupos_disponibles;

    @Column(name = "costo", nullable = false)
    private Integer costo;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fecha_inicio;

    @Column(name = "fecha_fin", nullable = false)
    private Date fecha_fin;

    public Curso() {
    }

    public Curso(Long id, String nombre, String descripcion, Integer nivel_educativo, String materia, Integer costo, Integer cupos_disponibles, Date fecha_inicio, Date fecha_fin) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel_educativo = nivel_educativo;
        this.materia = materia;
        this.cupos_disponibles = cupos_disponibles;
        this.costo = costo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNivel_educativo() {
        return nivel_educativo;
    }

    public void setNivel_educativo(Integer nivel_educativo) { this.nivel_educativo = nivel_educativo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setCosto(Integer costo){
        this.costo = costo;
    }

    public Integer getCosto(){
        return costo;
    }

    public void setCupos_disponibles(Integer cupos_disponibles){ this.cupos_disponibles = cupos_disponibles;}

    public Integer getCupos_disponibles() { return cupos_disponibles;}

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}