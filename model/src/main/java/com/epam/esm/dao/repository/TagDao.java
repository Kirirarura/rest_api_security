package com.epam.esm.dao.repository;


import com.epam.esm.dao.repository.custom.TagDaoCustom;
import com.epam.esm.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * An interface that represents relation with database, mainly working with Tags table.
 */
@Repository
public interface TagDao extends JpaRepository<Tag, Long>, TagDaoCustom {

    /**
     * Method to get tag entity from database by providing name of specific tag entity.
     *
     * @param name String name.
     * @return Optional of Tag entity.
     */
    Optional<Tag> findByName(String name);

}
