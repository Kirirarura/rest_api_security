package com.epam.esm.dao.query.impl;

import com.epam.esm.dao.query.AbstractQueryBuilder;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.esm.dao.query.FilterParams.NAME;
import static com.epam.esm.dao.query.FilterParams.TAG_NAME;
@Component
public class GiftCertificateQueryBuilder extends AbstractQueryBuilder<GiftCertificate> {

    private static final Class<GiftCertificate> GIFT_CERTIFICATE_CLASS = GiftCertificate.class;
    private static final String TAGS = "tags";
    @Override
    public CriteriaQuery<GiftCertificate> createGetQuery(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GIFT_CERTIFICATE_CLASS);
        Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GIFT_CERTIFICATE_CLASS);

        List<Predicate> restrictions = new ArrayList<>();

        restrictions.addAll(addName(fields, criteriaBuilder, giftCertificateRoot));
        restrictions.addAll(addTagNames(fields, criteriaBuilder, giftCertificateRoot));
        restrictions.addAll(addPartOfName(fields, criteriaBuilder, giftCertificateRoot));
        restrictions.addAll(addPartOfDescription(fields, criteriaBuilder, giftCertificateRoot));
        criteriaQuery.select(giftCertificateRoot).where(restrictions.toArray(new Predicate[]{}));
        addSortByName(fields, criteriaBuilder, criteriaQuery, giftCertificateRoot);

        return criteriaQuery;
    }

    private List<Predicate> addTagNames(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder,
                                        Root<GiftCertificate> giftCertificateRoot) {
        List<Predicate> restrictions = new ArrayList<>();

        List<String> tagNames = fields.get(TAG_NAME);
        if (tagNames != null) {
            restrictions = Arrays.stream(tagNames.toArray())
                    .map(tagName -> criteriaBuilder.equal(giftCertificateRoot.join(TAGS).get(String.valueOf(NAME)), tagName))
                    .toList();
        }
        return restrictions;
    }
}
