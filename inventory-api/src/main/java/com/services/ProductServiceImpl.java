package com.services;

import com.exceptions.ResourceFoundException;
import com.exceptions.ResourceNotFoundException;
import com.models.Product;
import com.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "product Service")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Product> findAll()
    {
        List<Product> list = new ArrayList<>();

        productsRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Product findProductById (long id)
    {
        return productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        productsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found!"));
        productsRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Product save (Product product)
    {
        Product newProduct = new Product();

        if (product.getId() != 0)
        {
            productsRepository.findById(product.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product id " + product.getId() + " not found"));
        }

        newProduct.setProductname(product.getProductname());
        newProduct.setDiscription(product.getDiscription());

        return productsRepository.save(newProduct);
    }

    public Product update (
            Product product,
            long id)
    {
        Product currentProduct = findProductById(id);

        if (product.getProductname() != null)
        {
            currentProduct.setProductname(product.getProductname().toLowerCase());
        }

        if (product.getCount() > 0)
        {
            throw new ResourceFoundException("Product Count cannot be updated through this process");
        }

        if (product.getDiscription() != null)
        {
            currentProduct.setDiscription(product.getDiscription());
        }

        return productsRepository.save(currentProduct);
    }

    @Override
    public Product addInventory( long id,long count) {
        Product currProduct = productsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found!" ));
        currProduct.setCount(currProduct.getCount() + count);
        return currProduct;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        productsRepository.deleteAll();
    }
}
