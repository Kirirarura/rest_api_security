package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.util.Optional;


/**
 * An interface that represents relation with database, mainly working with Gift Certificates table.
 */
public interface GiftCertificateDao extends CRUDDao<GiftCertificate>{


    /**
     * Method to get gift certificate entity from database by providing name of specific gift certificate entity.
     *
     * @param name String name.
     * @return Optional of gift certificate entity.
     */
    Optional<GiftCertificate> findByName(String name) ;

}
