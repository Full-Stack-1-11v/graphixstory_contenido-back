package com.graphixtory.educacion.educacion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;

import com.graphixtory.educacion.educacion.controller.ContenidoControllerV2;
import com.graphixtory.educacion.educacion.model.Contenido;


@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel <Contenido>>{

    @Override
    public EntityModel<Contenido> toModel(Contenido contenido){
        Link selfLink = linkTo(methodOn(ContenidoControllerV2.class).obtener(contenido.getId())).withSelfRel();

        Link allContenidosLink = linkTo(methodOn(ContenidoControllerV2.class).listar()).withRel("contenidos");

        Link crearLink = linkTo(methodOn(ContenidoControllerV2.class).crear(contenido))
        .withRel("crear")
        .withType("POST");

        Link actualizarLink = linkTo(methodOn(ContenidoControllerV2.class).actualizar(contenido.getId(), contenido))
        .withRel("actualizar")
        .withType("PUT");

        Link eliminarLink = linkTo(methodOn(ContenidoControllerV2.class).eliminar(contenido.getId()))
        .withRel("eliminar")
        .withType("DELETE");

        return EntityModel.of(contenido, selfLink, allContenidosLink, crearLink, actualizarLink, eliminarLink);
    }

}
