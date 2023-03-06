package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(collectionRelation = "giftCertificates", itemRelation = "giftCertificate")
public class GiftCertificateDto extends RepresentationModel<GiftCertificateDto> {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String lastUpdateDate;

    private CollectionModel<TagDto> tags;
}
