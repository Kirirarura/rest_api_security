package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.IncorrectParameterException;
import com.epam.esm.request.OrderCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    OrderDao orderDao;

    @Mock
    UserDao userDao;

    @Mock
    GiftCertificateDao giftCertificateDao;

    @InjectMocks
    OrderServiceImpl orderService;

    private static final String PURCHASE_DATE = String.valueOf(LocalDateTime.parse("2018-08-29T06:12:15.156"));

    private Order getOrder() {
        return new Order(1L, new BigDecimal("15.2"), PURCHASE_DATE,
                new User(1L, "firstName", "lastName"),
                new GiftCertificate(1L, "giftCertificate1", "description1", new BigDecimal("10.1"),
                        1, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null));
    }

    private List<Order> getOrders() {
        return Arrays.asList(
                new Order(1L, new BigDecimal("15.2"), PURCHASE_DATE,
                        new User(1L, "firstName", "lastName"),
                        new GiftCertificate(1L, "giftCertificate1", "description1", new BigDecimal("10.1"),
                                10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null)),
                new Order(2L, new BigDecimal("20.2"), PURCHASE_DATE,
                        new User(1L, "firstName", "lastName"),
                        new GiftCertificate(2L, "giftCertificate2", "description2", new BigDecimal("20.1"),
                                20, String.valueOf(Instant.now()), String.valueOf(Instant.now()), null)));
    }

    private static final int PAGE = 0;
    private static final int SIZE = 5;

    @Test
    void getById() {
        Order order = getOrder();
        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));

        Order actual = orderService.getById(order.getId());
        assertEquals(order, actual);
    }

    @Test
    void create() {
        Order order = getOrder();
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(
                order.getUser().getId(), order.getGiftCertificate().getId());
        when(userDao.findById(order.getUser().getId()))
                .thenReturn(Optional.of(order.getUser()));
        when(giftCertificateDao.findById(order.getGiftCertificate().getId()))
                .thenReturn(Optional.of(order.getGiftCertificate()));
        when(orderDao.insert(any())).thenReturn(order);

        Order actual = orderService.create(orderCreateRequest);
        assertEquals(order, actual);
    }

    @Test
    void getOrdersByUserId() {
        List<Order> orders = getOrders();
        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        when(orderDao.findByUserId(1L, pageRequest)).thenReturn(orders);

        List<Order> actual = orderService.getOrdersByUserId(1L, PAGE, SIZE);
        assertEquals(orders, actual);
    }

    @Test
    void getOrdersByUserIdThrowException() {
        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        lenient().when(orderDao.findByUserId(-1L, pageRequest)).thenThrow(IncorrectParameterException.class);

        assertThrows(IncorrectParameterException.class, () -> orderService.getOrdersByUserId(-1L, PAGE, SIZE));
    }
}