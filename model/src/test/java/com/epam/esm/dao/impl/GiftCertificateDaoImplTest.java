package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.config.TestDatabaseConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseConfig.class)
@ActiveProfiles("test")
@Transactional
class GiftCertificateDaoImplTest {

    @Autowired
    private GiftCertificateDao giftCertificateDao;

    private GiftCertificate getGiftCertificate() {
        return new GiftCertificate(null, "test", "description1", BigDecimal.valueOf(10.1),
                10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null);
    }

    private static final Long ID = 10L;

    @Test
    void insertSuccess() {
        GiftCertificate giftCertificate = getGiftCertificate();
        GiftCertificate insert = giftCertificateDao.insert(giftCertificate);

        Optional<GiftCertificate> result = giftCertificateDao.findById(giftCertificate.getId());
        Assertions.assertEquals(result.get().getName(), giftCertificate.getName());
    }

    @Test
    void getById() {
        Optional<GiftCertificate> actual = giftCertificateDao.findById(10L);
        Assertions.assertEquals(actual.get().getName(), "giftCertificate1");
    }

    @Test
    void updatePriceSuccess() {
        GiftCertificate giftCertificateToUpdate = giftCertificateDao.findById(ID).orElseThrow();
        BigDecimal updatePrice = BigDecimal.valueOf(20.1);
        giftCertificateToUpdate.setPrice(updatePrice);

        giftCertificateDao.update(giftCertificateToUpdate);
        Optional<GiftCertificate> result = giftCertificateDao.findById(ID);
        Assertions.assertEquals(result.get().getPrice(), updatePrice);
    }

    @Test
    void updateDescriptionSuccess() {
        GiftCertificate giftCertificateToUpdate = giftCertificateDao.findById(ID).orElseThrow();
        String updateDescription = "new description";
        giftCertificateToUpdate.setDescription(updateDescription);

        giftCertificateDao.update(giftCertificateToUpdate);
        Optional<GiftCertificate> result = giftCertificateDao.findById(ID);
        Assertions.assertEquals(result.get().getDescription(), updateDescription);
    }

    @Test
    void deleteSuccess() {
        giftCertificateDao.removeById(ID);
        GiftCertificate giftCertificate = giftCertificateDao.findById(ID).orElse(null);
        Assertions.assertEquals(giftCertificate, null);
    }
//
//    @Test
//    void doFilter() {
//        Map<String, List<String>> map = new HashMap<>();
//        List<String> tags = new ArrayList<>();
//        tags.add("tags");
//        map.put("tagName", tags);
//
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        List<GiftCertificate> expected = Arrays.asList(
//                new GiftCertificate(11L, "giftCertificate2", "description2", BigDecimal.valueOf(15.12), 20,
//                        "2023-02-16T09:47:04.325974800Z", "2023-02-16T09:47:04.325974800Z",
//                        List.of(new Tag(2L, "tag2"))));
//
//        List<GiftCertificate> actual = giftCertificateDao.findWithFilters(new MultiValueMapAdapter<>(map), pageRequest);
//        Assertions.assertEquals(expected, actual);
//    }


}
