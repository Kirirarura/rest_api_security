package com.epam.esm.dao.repository.custom;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class TagDaoCustomImpl implements TagDaoCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public TagDaoCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
