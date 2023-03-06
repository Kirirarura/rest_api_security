package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.request.GiftCertificateCreateRequest;
import com.epam.esm.request.GiftCertificatePriceUpdateRequest;
import com.epam.esm.request.GiftCertificateUpdateRequest;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate create(GiftCertificateCreateRequest request);
    GiftCertificate getById(Long id);
    void deleteById(Long id);
    GiftCertificate update(Long id, GiftCertificateUpdateRequest updateRequest)
            throws NoSuchEntityException;
    GiftCertificate update(Long id, GiftCertificatePriceUpdateRequest updateRequest);
    List<GiftCertificate> doFilter(MultiValueMap<String, String> requestParams, int page, int size);
    void fillData();
}
