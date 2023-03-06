package com.epam.esm.controllers;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import com.epam.esm.hateoas.OrderHateoas;
import com.epam.esm.request.OrderCreateRequest;
import com.epam.esm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller responsible for all operations with orders.
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderHateoas orderHateoas;
    private final OrderService orderService;


    @GetMapping("/{id}")
    public OrderDto orderById(@PathVariable Long id) {
        return orderHateoas.toModel(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        OrderDto orderDTO = orderHateoas.toModel(
                orderService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, orderDTO.getLink("self").orElseThrow().toUri().toString())
                .body(orderDTO);
    }

    @GetMapping("/users/{userId}")
    public CollectionModel<OrderDto> ordersByUserId(@PathVariable Long userId,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
        List<Order> orders = orderService.getOrdersByUserId(userId, page, size);
        return orderHateoas.toCollectionModel(orders);
    }

    @PostMapping("/fillData")
    public void fillData(){
        orderService.fillData();
    }

}
