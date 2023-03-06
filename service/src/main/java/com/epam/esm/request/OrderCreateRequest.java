package com.epam.esm.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class OrderCreateRequest {

    Long userId;
    Long giftCertificateId;
}
