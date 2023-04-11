package com.epam.esm;

import com.epam.esm.dao.repository.GiftCertificateDao;
import com.epam.esm.dao.repository.OrderDao;
import com.epam.esm.dao.repository.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderDaoImplTest {

    @Autowired
    private OrderDao orderRepository;
    @Autowired
    private UserDao userRepository;
    @Autowired
    private GiftCertificateDao giftCertificateRepository;



    private Order getOrder() {
        return new Order(null, BigDecimal.valueOf(12.00), String.valueOf(Instant.now()),
                new User(), new GiftCertificate());
    }

    @Test
    void insertSuccess() {
        Order expected = getOrder();
        User user1 = userRepository.findByUsername("user1").orElse(null);
        GiftCertificate giftCertificate1 = giftCertificateRepository.findByName("giftCertificate1").orElse(null);
        expected.setUser(user1);
        expected.setGiftCertificate(giftCertificate1);

        orderRepository.save(expected);
        Order actual = orderRepository.findById(expected.getId()).orElse(null);

        assert actual != null;
        Assertions.assertEquals(actual.getId(), expected.getId());
    }

    @Test
    void findByUserId() {
        List<Order> ordersByUserId = orderRepository.findByUserId(1L, Pageable.unpaged());

        assertThat(ordersByUserId).hasSize(3);
    }
}