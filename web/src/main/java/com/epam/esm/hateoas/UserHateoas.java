package com.epam.esm.hateoas;

import com.epam.esm.controllers.UsersController;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * HATEOAS module for User entity.
 */
@Component
@RequiredArgsConstructor
public class UserHateoas implements RepresentationModelAssembler<User, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto toModel(User entity) {
        UserDto userDto = modelMapper.map(entity, UserDto.class);
        userDto.add(linkTo(methodOn(UsersController.class).userById(entity.getId())).withSelfRel());
        return userDto;
    }

    @Override
    public CollectionModel<UserDto> toCollectionModel(Iterable<? extends User> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
