package com.epam.esm.dao.repository.custom;

import com.epam.esm.entity.Tag;

import java.util.Optional;

public interface TagDaoCustom {
    Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders(Long id);
}
