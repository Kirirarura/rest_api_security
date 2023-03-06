package com.epam.esm.request;

import com.epam.esm.entity.Tag;
import com.epam.esm.validation.giftcertificate.optional.*;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@AllArgsConstructor
public class GiftCertificateUpdateRequest {
    @OptionalName String name;

    @OptionalDescription String description;

    @OptionalPrice BigDecimal price;

    @OptionalDuration Integer duration;

    @OptionalTags List<Tag> tags;

    public boolean isNamePresent() {
        return name != null;
    }

    public boolean isDescriptionPresent() {
        return description != null;
    }

    public boolean isPricePresent() {
        return price != null;
    }

    public boolean isDurationPresent() {
        return duration != null;
    }

    public boolean isTagsPresent() {
        return tags != null;
    }

}
