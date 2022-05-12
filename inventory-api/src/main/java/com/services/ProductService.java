package com.services;

import com.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findProductById(long id);

    void delete(long id);

    Product save(Product product);

    Product update(
            Product product,
            long id);

    Product addInventory(long id,
                         long count);

    void deleteAll();
}
