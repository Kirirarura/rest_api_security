package com.epam.esm.request;

import com.epam.esm.validation.giftcertificate.Price;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class GiftCertificatePriceUpdateRequest {
    @Price BigDecimal price;
}

