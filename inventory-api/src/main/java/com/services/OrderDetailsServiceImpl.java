package com.services;

import com.exceptions.ResourceNotFoundException;
import com.models.Order;
import com.models.OrderDetails;
import com.models.OrderDetailsId;
import com.models.Product;
import com.repository.OrderDetailsRepository;
import com.repository.OrderRepository;
import com.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "orderdetailService")
public class OrderDetailsServiceImpl {
    @Autowired
    ProductService productServ;

    @Autowired
    ProductsRepository productsRepo;

    @Autowired
    OrderDetailsRepository detailsRepository;

    @Autowired
    OrderRepository orderRepo;

//    @Override
    public OrderDetails addToOrder(long productid, long orderId)
    {
//        Product addProduct = productsRepo.findById(productid).orElseThrow(()-> new ResourceNotFoundException("Product with id " + productid + " not found!"));
//
//        Order currOrder = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order with id "+ orderId + " not found!"));
//
//        OrderDetails addOrderDetail = detailsRepository.findById(new OrderDetailsId(productid, ));
//        return detailsRepository.save(addOrderDetail);
        return new OrderDetails();
    }

//    @Override
    public OrderDetails removeFromOrder(long productid)
    {
//        OrderDetails workingOrderItem = detailsRepository.findById(new OrderDetailsId());
//        if ( workingOrderItem.ge)
//
        return new OrderDetails();
    }
}
