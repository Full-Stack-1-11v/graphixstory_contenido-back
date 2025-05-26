package com.graphixtory.educacion.educacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name= "material_educativo")
public class MaterialEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "titulo_material", nullable = false)
    private String tituloMaterial;

    @Column(name = "tipo_recurso", nullable = false)
    private String tipoRecurso;

    @Column(name = "estado_publicacion", nullable = false)
    private String estadoPublicacion;

    @Column(name = "estado_material", nullable = false)
    private String estadoMaterial;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    public MaterialEducativo() {
    }

    public MaterialEducativo(Long id, Curso curso, String tituloMaterial, String tipoRecurso, String estadoPublicacion, String estadoMaterial, Date fechaCreacion) {
        this.id = id;
        this.curso = curso;
        this.tituloMaterial = tituloMaterial;
        this.tipoRecurso = tipoRecurso;
        this.estadoPublicacion = estadoPublicacion;
        this.estadoMaterial = estadoMaterial;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTituloMaterial() {
        return tituloMaterial;
    }

    public void setTituloMaterial(String tituloMaterial) {
        this.tituloMaterial = tituloMaterial;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public String getEstadoMaterial() {
        return estadoMaterial;
    }

    public void setEstadoMaterial(String estadoMaterial) {
        this.estadoMaterial = estadoMaterial;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}