package com.epam.esm.dao.repository;

import com.epam.esm.dao.repository.custom.GiftCertificateDaoCustom;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * An interface that represents relation with database, mainly working with Gift Certificates table.
 */
@Repository
public interface GiftCertificateDao extends JpaRepository<GiftCertificate, Long>, GiftCertificateDaoCustom {


    /**
     * Method to get gift certificate entity from database by providing name of specific gift certificate entity.
     *
     * @param name String name.
     * @return Optional of gift certificate entity.
     */
    Optional<GiftCertificate> findByName(String name) ;

}
