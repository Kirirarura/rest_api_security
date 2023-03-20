package com.epam.esm.dao.impl;

import com.epam.esm.dao.repository.GiftCertificateDao;
import com.epam.esm.dao.config.TestDatabaseConfig;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseConfig.class)
@ActiveProfiles("test")
@Transactional
class GiftCertificateDaoImplTest {

//    @Autowired
//    private GiftCertificateDao giftCertificateDao;
//
//    private GiftCertificate getGiftCertificate() {
//        return new GiftCertificate(null, "test", "description1", BigDecimal.valueOf(10.1),
//                10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null);
//    }
//
//    private static final Long ID = 10L;
//
//    @Test
//    void insertSuccess() {
//        GiftCertificate giftCertificate = getGiftCertificate();
//        GiftCertificate insert = giftCertificateDao.save(giftCertificate);
//
//        Optional<GiftCertificate> result = giftCertificateDao.findById(giftCertificate.getId());
//        Assertions.assertEquals(result.get().getName(), giftCertificate.getName());
//    }
//
//    @Test
//    void getById() {
//        Optional<GiftCertificate> actual = giftCertificateDao.findById(10L);
//        Assertions.assertEquals(actual.get().getName(), "giftCertificate1");
//    }
//
//    @Test
//    void updatePriceSuccess() {
//        GiftCertificate giftCertificateToUpdate = giftCertificateDao.findById(ID).orElseThrow();
//        BigDecimal updatePrice = BigDecimal.valueOf(20.1);
//        giftCertificateToUpdate.setPrice(updatePrice);
//
//        giftCertificateDao.save(giftCertificateToUpdate);
//        Optional<GiftCertificate> result = giftCertificateDao.findById(ID);
//        Assertions.assertEquals(result.get().getPrice(), updatePrice);
//    }
//
//    @Test
//    void updateDescriptionSuccess() {
//        GiftCertificate giftCertificateToUpdate = giftCertificateDao.findById(ID).orElseThrow();
//        String updateDescription = "new description";
//        giftCertificateToUpdate.setDescription(updateDescription);
//
//        giftCertificateDao.save(giftCertificateToUpdate);
//        Optional<GiftCertificate> result = giftCertificateDao.findById(ID);
//        Assertions.assertEquals(result.get().getDescription(), updateDescription);
//    }
//
//    @Test
//    void deleteSuccess() {
//        giftCertificateDao.deleteById(ID);
//        GiftCertificate giftCertificate = giftCertificateDao.findById(ID).orElse(null);
//        Assertions.assertEquals(giftCertificate, null);
//    }
}
