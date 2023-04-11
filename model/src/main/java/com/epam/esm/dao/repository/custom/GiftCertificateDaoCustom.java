package com.epam.esm.dao.repository.custom;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface GiftCertificateDaoCustom {
    List<GiftCertificate> findWithFilters(MultiValueMap<String, String> fields, Pageable pageable);
}
