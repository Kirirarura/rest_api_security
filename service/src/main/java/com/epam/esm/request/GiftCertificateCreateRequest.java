package com.epam.esm.request;

import com.epam.esm.entity.Tag;
import com.epam.esm.validation.giftcertificate.*;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@AllArgsConstructor
public class GiftCertificateCreateRequest {

    @Name
    String name;

    @Description
    String description;

    @Price
    BigDecimal price;

    @Duration
    Integer duration;

    @Tags
    List<Tag> tags;
}
