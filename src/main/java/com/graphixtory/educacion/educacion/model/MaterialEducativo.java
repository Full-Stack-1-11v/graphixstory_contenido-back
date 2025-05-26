package com.graphixtory.educacion.educacion.model;

import jakarta.persistence.*;
import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name= "material_educativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}