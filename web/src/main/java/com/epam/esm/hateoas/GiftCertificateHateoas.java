package com.epam.esm.hateoas;

import com.epam.esm.controllers.GiftCertificatesController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * HATEOAS module for Gift Certificate entity.
 */
@Component
@RequiredArgsConstructor
public class GiftCertificateHateoas implements RepresentationModelAssembler<GiftCertificate, GiftCertificateDto> {

    private final TagHateoas tagHateoas;
    private final ModelMapper modelMapper;
    @Override
    public GiftCertificateDto toModel(GiftCertificate entity) {
        GiftCertificateDto giftCertificateDTO = modelMapper.map(entity, GiftCertificateDto.class);
        giftCertificateDTO.setTags(tagHateoas.toCollectionModel(entity.getTags()));
        Link selfLink = linkTo(methodOn(GiftCertificatesController.class).giftCertificateById(
                entity.getId())).withSelfRel();
        giftCertificateDTO.add(selfLink);
        return giftCertificateDTO;
    }

    @Override
    public CollectionModel<GiftCertificateDto> toCollectionModel(Iterable<? extends GiftCertificate> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
