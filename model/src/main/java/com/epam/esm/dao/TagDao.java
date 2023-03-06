package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.Optional;

/**
 * An interface that represents relation with database, mainly working with Tags table.
 */
public interface TagDao extends CRDDao<Tag>{

    /**
     * Method to get tag entity from database by providing name of specific tag entity.
     *
     * @param name String name.
     * @return Optional of Tag entity.
     */
    Optional<Tag> findByName(String name);
    Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders(Long id);
}
