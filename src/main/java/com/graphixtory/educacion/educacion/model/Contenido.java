package com.graphixtory.educacion.educacion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Schema(description = "Representa un contenido educativo en el sistema")
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del contenido", example = "1")
    private Long id;

    @Schema(description = "Nombre o título del contenido", example = "Introducción a la Programación")
    private String nombre;

    @Schema(description = "Nivel educativo al que está dirigido el contenido (e.g., Primaria, Secundaria, Universidad)", example = "Universidad")
    private String nivel_educativo;

    @Schema(description = "Materia o asignatura a la que pertenece el contenido (e.g., Matemáticas, Historia, Informática)", example = "Informática")
    private String materia;

    @Schema(description = "Fecha y hora de inicio de validez del contenido", example = "2024-01-01T09:00:00")
    private LocalDateTime fecha_inicio;

    @Schema(description = "Fecha y hora de fin de validez del contenido", example = "2024-12-31T23:59:59")
    private LocalDateTime fecha_fin;

    // Constructor para uso en tests (si usas @Data, Lombok genera uno, pero para List.of y mocks puede ser útil)
    public Contenido(Long id, String nombre, String nivel_educativo, String materia, LocalDateTime fecha_inicio, LocalDateTime fecha_fin) {
        this.id = id;
        this.nombre = nombre;
        this.nivel_educativo = nivel_educativo;
        this.materia = materia;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Contenido() {
    }
}