package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.esm.dao.query.Queries.FIND_BY_USER_ID_QUERY;

/**
 * Implementation of OrderDao interface to perform basic actions with database.
 * Mainly, actions with Orders.
 */
@Repository
@Transactional
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    protected QueryBuilder<Order> getQueryCreator() {
        return null;
    }

    @Override
    public List<Order> findByUserId(Long userId, Pageable pageable) {
        return entityManager.createQuery(FIND_BY_USER_ID_QUERY, entityType)
                .setParameter("user_id", userId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
