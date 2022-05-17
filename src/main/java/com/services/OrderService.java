package com.services;

import com.models.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> findAll();

    Order findOrderById(long id);

    void deleteOrder(long id);


}
