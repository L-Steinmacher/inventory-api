package com;

import com.models.Product;
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
        productService.save(p1);
        productService.save(p2);
        productService.save(p3);

        productService.addInventory(1, 100);
        productService.addInventory(2, 200);
        productService.addInventory(3,300);
    }


}
