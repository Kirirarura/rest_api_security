package com.epam.esm.hateoas;

import com.epam.esm.controllers.TagsController;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * HATEOAS module for Tag entity.
 */
@Component
@RequiredArgsConstructor
public class TagHateoas implements RepresentationModelAssembler<Tag, TagDto> {

    private final ModelMapper modelMapper;

    @Override
    public TagDto toModel(Tag entity) {
        TagDto tagDTO = modelMapper.map(entity, TagDto.class);
        Link selfLink = linkTo(methodOn(TagsController.class).getById(entity.getId())).withSelfRel();
        tagDTO.add(selfLink);
        return tagDTO;
    }

    @Override
    public CollectionModel<TagDto> toCollectionModel(Iterable<? extends Tag> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
