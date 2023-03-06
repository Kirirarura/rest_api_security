package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementation of GiftCertificateDao interface to perform basic actions with database.
 * Mainly, actions with Gift Certificates.
 */
@Repository
@Transactional
public class GiftCertificateDaoImpl extends AbstractDao<GiftCertificate> implements GiftCertificateDao{

    private final QueryBuilder<GiftCertificate> queryBuilder;
    @Autowired
    public GiftCertificateDaoImpl(QueryBuilder<GiftCertificate> queryBuilder) {
        super(GiftCertificate.class);
        this.queryBuilder = queryBuilder;
    }

    @Override
    public GiftCertificate update(GiftCertificate item) {
        return entityManager.merge(item);
    }

    @Override
    protected QueryBuilder<GiftCertificate> getQueryCreator() {
        return queryBuilder;
    }
}
