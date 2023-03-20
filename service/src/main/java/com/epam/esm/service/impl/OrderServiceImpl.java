package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.GiftCertificateDao;
import com.epam.esm.dao.repository.OrderDao;
import com.epam.esm.dao.repository.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.ExceptionResult;
import com.epam.esm.exception.IncorrectParameterException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.request.OrderCreateRequest;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.impl.util.PaginationHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.esm.exception.ExceptionMessageKey.BAD_USER_ID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;

    private static Supplier<NoSuchEntityException> getNoSuchOrderException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    private static Supplier<NoSuchEntityException> getNoSuchUserException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    private static Supplier<NoSuchEntityException> getNoSuchGiftCertificateException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.findById(id).orElseThrow(getNoSuchOrderException(id));
    }

    @Override
    @Transactional
    public Order create(OrderCreateRequest request) {
        User user = userDao.findById(request.getUserId())
                .orElseThrow(getNoSuchUserException(request.getUserId()));

        GiftCertificate giftCertificate = giftCertificateDao.findById(request.getGiftCertificateId())
                .orElseThrow(getNoSuchGiftCertificateException(request.getGiftCertificateId()));

        Order order = new Order(null, giftCertificate.getPrice(),
                String.valueOf(Timestamp.from(Instant.now())), user, giftCertificate);
        return orderDao.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId, int page, int size) {
        if (userId < 1) {
            ExceptionResult exceptionResult = new ExceptionResult();
            exceptionResult.addException(BAD_USER_ID, userId);
            throw new IncorrectParameterException(exceptionResult);
        }
        Pageable pageRequest = PaginationHelper.createPageRequest(page, size);
        return orderDao.findByUserId(userId, pageRequest);
    }

    @Override
    @Transactional
    public void fillData() {
        for (int i = 1; i <= 500; i++) {
            OrderCreateRequest firstRequest = new OrderCreateRequest((long) i, i + 35123L);
            create(firstRequest);
            OrderCreateRequest secondRequest = new OrderCreateRequest((long) i, i + 35124L);
            create(secondRequest);
        }
    }
}
