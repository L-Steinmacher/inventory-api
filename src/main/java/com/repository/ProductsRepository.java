package com.repository;

import com.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository
        extends CrudRepository<Product, Long> {
}
