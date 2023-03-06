package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Optional;


/**
 * Implementation of TagDao interface to perform basic actions with database.
 * Mainly, actions with Tags.
 */
@Repository
@Transactional
public class TagDaoImpl extends AbstractDao<Tag> implements TagDao {

    private final QueryBuilder<Tag> queryBuilder;

    @Autowired
    public TagDaoImpl(QueryBuilder<Tag> queryBuilder) {
        super(Tag.class);
        this.queryBuilder = queryBuilder;
    }

    @Override
    protected QueryBuilder<Tag> getQueryCreator() {
        return queryBuilder;
    }

    @Override
    public Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Join<Tag, Order> tagOrderJoin = root.join("giftCertificate").join("tags");

        criteriaQuery.select(root.get("giftCertificate").get("tags"))
                .where(criteriaBuilder.equal(root.get("user").get("id"), id))
                .groupBy(tagOrderJoin.get("id"))
                .orderBy(criteriaBuilder.desc(criteriaBuilder.count(tagOrderJoin.get("id"))),
                        criteriaBuilder.desc(criteriaBuilder.sum(root.get("price"))));

        return entityManager.createQuery(criteriaQuery)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }
}
