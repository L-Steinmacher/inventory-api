package com.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.models.Order;
import com.models.OrderItem;
import com.models.OrderMinimum;
import com.services.OrderDetailsService;
import com.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @GetMapping(value = "/orders",
            produces = "application/json")
    public ResponseEntity<?> listAllOrders()
    {
        List<Order> list = orderService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//
    @PostMapping(value = "/customer/")
    public ResponseEntity<?> addItemsToOrder(
            @RequestBody OrderMinimum order
            )
    {
        Order newOrder = new Order();
        newOrder.setCustomerid(order.getCustomerid());
        newOrder.setComments(order.getComments());
        newOrder = orderService.save(newOrder);
        for (OrderItem item: order.getItems())
        {
            System.out.println(item);
            orderDetailsService.addToOrder(item.getProductid(), newOrder.getOrderid(), item.getQuantity());
        }

        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }
}
