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

@Transactional
@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
@Component
public class SeedData implements CommandLineRunner {
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
                "hat",
                "put it on ya head");

        Product p2 = new Product(
                "clipboard",
                "put ya notes on it"
        );

        Product p3 = new Product(
                "cellphone",
                "it looks up cat pictures"
        );
        p1 = productService.save(p1);
        p2 = productService.save(p2);
        p3 = productService.save(p3);

        productService.addInventory(1, 100);
        productService.addInventory(2, 200);
        productService.addInventory(3,300);

        Order o1 = new Order("sammy","will pick up whenever");
        Order o2 = new Order("douglas","fedex to address");

        o1 = orderService.save(o1);
        o2 = orderService.save(o2);

        orderDetailsService.addToOrder(p1.getProductid(), o1.getOrderid());

        for (int i = 0; i < 4; i++)
        {
            orderDetailsService.addToOrder(p1.getProductid(), o2.getOrderid());
            orderDetailsService.addToOrder(p2.getProductid(), o2.getOrderid());
        }

    }


}
