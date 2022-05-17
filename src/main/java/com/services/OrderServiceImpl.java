package com.services;

import com.exceptions.ResourceFoundException;
import com.exceptions.ResourceNotFoundException;
import com.models.Order;
import com.models.OrderDetails;
import com.models.Product;
import com.repository.OrderRepository;
import com.repository.ProductsRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl
        implements OrderService
{
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    ProductService productService;

    @Transactional
    @Override
    public Order save(Order order) {
        if (order.getProducts().size() > 0 )
        {
            throw new ResourceFoundException("Products are not created via Orders");
        }
        Order newOrder = new Order();

        if (order.getOrderid() != 0)
        {
            newOrder = orderRepo.findById(order.getOrderid())
                    .orElseThrow(() -> new ResourceNotFoundException("Order with id " + order.getOrderid() + " not found!"));
        }

        newOrder.setCustomerid(order.getCustomerid());
        newOrder.setComments(order.getComments());
        newOrder.getProducts().clear();

        return orderRepo.save(newOrder);

    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        orderRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Order findOrderById(long id) {

        return orderRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order with id " + id + " not found!"));
    }

    @Override
    public void deleteOrder(long id) {
        orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found!"));
        orderRepo.deleteById(id);
    }
}
