package com.epam.esm.dao.impl;

import com.epam.esm.dao.repository.OrderDao;
import com.epam.esm.dao.config.TestDatabaseConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
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
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseConfig.class)
@ActiveProfiles("test")
@Transactional
class OrderDaoImplTest {

//    private final static Long ID = 1L;
//
//    private Order getOrder() {
//        return new Order(null, BigDecimal.valueOf(12.00), String.valueOf(Instant.now()),
//                new User(), new GiftCertificate());
//    }
//
//    @Autowired
//    OrderDao orderDao;
//
//    @Test
//    void insertSuccess() {
//        Order order = getOrder();
//        order.getGiftCertificate().setId(10L);
//        order.getUser().setId(ID);
//        orderDao.save(order);
//
//        Optional<Order> result = orderDao.findById(order.getId());
//        Assertions.assertEquals(result.get().getId(), order.getId());
//    }
//
//    @Test
//    void findByUserId() {
//        Tag tags1[] = {new Tag(2L, "tag2")};
//        Tag tags2[] = {};
//        List<Order> expected = Arrays.asList(
//                new Order(1L, BigDecimal.valueOf(15.12), "2023-02-16T09:47:04.325974800Z",
//                        new User(1L, "Marry", "Rose"),
//                        new GiftCertificate(11L, "giftCertificate2", "description2", BigDecimal.valueOf(15.12),
//                                20, "2023-02-16T09:47:04.325974800Z",
//                                "2023-02-16T09:47:04.325974800Z", List.of(tags1))),
//                new Order(3L, BigDecimal.valueOf(5.17), "2023-02-16T09:47:04.325974800Z",
//                        new User(1L, "Marry", "Rose"),
//                        new GiftCertificate(12L, "giftCertificate3", "description3", BigDecimal.valueOf(5.17),
//                                30, "2023-02-16T09:47:04.325974800Z",
//                                "2023-02-16T09:47:04.325974800Z",  List.of(tags2)))
//        );
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        List<Order> actual = orderDao.findByUserId(1L, pageRequest);
//
//        Assertions.assertEquals(expected, actual);
//    }
}