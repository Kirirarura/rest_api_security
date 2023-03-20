package com.epam.esm.dao.repository.custom;

import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class GiftCertificateDaoCustomImpl implements GiftCertificateDaoCustom{

    @PersistenceContext
    private final EntityManager entityManager;
    private final QueryBuilder<GiftCertificate> queryBuilder;

    @Autowired
    public GiftCertificateDaoCustomImpl(EntityManager entityManager, QueryBuilder<GiftCertificate> queryBuilder) {
        this.entityManager = entityManager;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public List<GiftCertificate> findWithFilters(MultiValueMap<String, String> fields, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteriaQuery = queryBuilder.createGetQuery(fields, criteriaBuilder);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
