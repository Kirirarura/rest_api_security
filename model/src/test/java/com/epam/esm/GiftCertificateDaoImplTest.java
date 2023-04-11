package com.epam.esm;

import com.epam.esm.dao.repository.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class GiftCertificateDaoImplTest {

    @Autowired
    private GiftCertificateDao repository;


    @BeforeEach
    void setUp(){
        repository.saveAllAndFlush(List.of(
                new GiftCertificate(null, "test", "description1", BigDecimal.valueOf(10.1),
                10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null)
        ));
    }

    @Test
    void findAllShouldProduceAllCertificates(){
        List<GiftCertificate> actual = repository.findAll();
        assertThat(actual).hasSize(4);
    }


    private GiftCertificate getGiftCertificate() {
        return new GiftCertificate(null, "test", "description1", BigDecimal.valueOf(10.1),
                10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null);
    }

    @Test
    void insertSuccess() {
        GiftCertificate giftCertificate = getGiftCertificate();
        GiftCertificate insertedGiftCertificate = repository.save(giftCertificate);

        Optional<GiftCertificate> actual = repository.findById(giftCertificate.getId());
        Assertions.assertEquals(insertedGiftCertificate.getName(), actual.get().getName());
    }

    @Test
    void getById() {
        Optional<GiftCertificate> actual = repository.findById(1L);
        Assertions.assertEquals("giftCertificate1", actual.get().getName());
    }

    @Test
    void updatePriceSuccess() {
        GiftCertificate giftCertificateToUpdate = repository.findById(1L).orElseThrow();
        BigDecimal updatePrice = BigDecimal.valueOf(20.1);
        giftCertificateToUpdate.setPrice(updatePrice);

        repository.save(giftCertificateToUpdate);
        Optional<GiftCertificate> result = repository.findById(1L);
        Assertions.assertEquals(result.get().getPrice(), updatePrice);
    }

    @Test
    void updateDescriptionSuccess() {
        GiftCertificate giftCertificateToUpdate = repository.findById(1L).orElseThrow();
        String updateDescription = "new description";
        giftCertificateToUpdate.setDescription(updateDescription);

        repository.save(giftCertificateToUpdate);
        Optional<GiftCertificate> result = repository.findById(1L);
        Assertions.assertEquals(result.get().getDescription(), updateDescription);
    }

    @Test
    void deleteSuccess() {
        repository.deleteById(2L);
        GiftCertificate giftCertificate = repository.findById(2L).orElse(null);
        Assertions.assertNull(giftCertificate);
    }
}
