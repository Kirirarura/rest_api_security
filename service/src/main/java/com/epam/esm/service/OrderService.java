package com.epam.esm.service;

import com.epam.esm.entity.Order;
import com.epam.esm.request.OrderCreateRequest;

import java.util.List;

public interface OrderService {

    Order getById(Long id);

    Order create(OrderCreateRequest request);

    List<Order> getOrdersByUserId(Long userId, int page, int size);

    void fillData();
}
