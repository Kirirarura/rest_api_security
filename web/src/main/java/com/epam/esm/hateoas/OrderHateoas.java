package com.epam.esm.hateoas;

import com.epam.esm.controllers.OrderController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * HATEOAS module for Order entity.
 */
@Component
@RequiredArgsConstructor
public class OrderHateoas implements RepresentationModelAssembler<Order, OrderDto> {

    private final ModelMapper modelMapper;

    @Override
    public OrderDto toModel(Order entity) {
        OrderDto orderDto = modelMapper.map(entity, OrderDto.class);
        Link selfLink = linkTo(methodOn(OrderController.class).orderById(entity.getId())).withSelfRel();
        orderDto.add(selfLink);
        return orderDto;
    }

    @Override
    public CollectionModel<OrderDto> toCollectionModel(Iterable<? extends Order> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
