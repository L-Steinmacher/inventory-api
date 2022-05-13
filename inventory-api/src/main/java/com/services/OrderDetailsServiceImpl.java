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

@Service(value = "orderdetailsService")
public class OrderDetailsServiceImpl
        implements OrderDetailsService
{

    @Autowired
    ProductsRepository productsRepo;

    @Autowired
    OrderDetailsRepository detailsRepository;

    @Autowired
    OrderRepository orderRepo;

    @Override
    public OrderDetails addToOrder(long productid, long orderid, long quantity)
    {
        Product workingProduct = productsRepo.findById(productid)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + productid + " not found!"));

        Order workingOrder = orderRepo.findById(orderid)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id "+ orderid + " not found!"));

        OrderDetails workingOrderDetail = detailsRepository.findById(new OrderDetailsId(productid,
                        orderid))
                .orElse(new OrderDetails(workingProduct,
                        workingOrder,
                        0));
        workingProduct.setCount(workingProduct.getCount()- quantity);

        workingOrderDetail.setQuantity(workingOrderDetail.getQuantity() + quantity);
        return detailsRepository.save(workingOrderDetail);

    }

    @Override
    public OrderDetails removeFromOrder(long productid, long orderid, long quantity) {
        Product workingProduct = productsRepo.findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productid + " not found!"));

        Order workingOrder = orderRepo.findById(orderid)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id "+ orderid + " not found!"));

        OrderDetails workingOrderDetail = detailsRepository.findById(new OrderDetailsId(productid, orderid))
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productid+ " not found on order!"));

        workingProduct.setCount(workingProduct.getCount() + quantity);
        workingOrderDetail.setQuantity(workingOrderDetail.getQuantity() - quantity);

        if (workingOrderDetail.getQuantity() <= 0)
        {
            detailsRepository.deleteById(new OrderDetailsId(productid,orderid));
            return null;
        }else {
            return detailsRepository.save(workingOrderDetail);
        }
    }

}
