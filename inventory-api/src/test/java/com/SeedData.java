package com;

import com.models.Order;
import com.models.Product;
import com.services.OrderDetailsService;
import com.services.OrderService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
@Component
public class SeedData
    implements CommandLineRunner {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {


        Product p1 = new Product(
                "testhat",
                "put it on ya head");

        Product p2 = new Product(
                "testclipboard",
                "put ya notes on it"
        );

        Product p3 = new Product(
                "testcellphone",
                "it looks up cat pictures"
        );

        Product p4 = new Product(
                "testwatch",
                "tells time "
        );
        p4.setCount(400);
        p1.setCount(100);
        p2.setCount(200);
        p3.setCount(300);
        p1 = productService.save(p1);
        p2 = productService.save(p2);
        p3 = productService.save(p3);
        p4 = productService.save(p4);

        productService.addInventory(1, 100);
        productService.addInventory(2, 200);
        productService.addInventory(3,300);

        List<Product> productList = productService.findAll();
        System.out.println("##################################");
        System.out.println(productList+ "    " + productList.size());

        for (Product p : productList)
        {
            System.out.println("Product id: "+ p.getProductid()+ "   prouct name: " + p.getProductname()+ "   count: "+p.getCount());
        }
        System.out.println("##################################");

        Order o1 = new Order(42,"will pick up whenever");
        Order o2 = new Order(43,"fedex to address");

        o1 = orderService.save(o1);
        o2 = orderService.save(o2);

        orderDetailsService.addToOrder(p1.getProductid(), o1.getOrderid(), 10);

        orderDetailsService.addToOrder(p2.getProductid(), o2.getOrderid(),20);
        orderDetailsService.addToOrder(p3.getProductid(), o2.getOrderid(),30);
    }
}
