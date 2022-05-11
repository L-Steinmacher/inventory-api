package com.controllers;

import com.models.Product;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/products",
        produces = "application/json")
    public ResponseEntity<?> listAllProducts()
    {
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{productId}",
        produces = "application/json")
    public ResponseEntity<?> getProductById(
            @PathVariable Long productId)
    {
        Product p = productService.findProductById(productId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{productId}",
        produces = "application/json")
    public ResponseEntity<?> deleteProductById (
            @PathVariable Long productId)
    {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/product",
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<?> addNewProduct(
            @Valid
            @RequestBody
                Product newProduct) throws URISyntaxException
    {
        newProduct = productService.save(newProduct);

        HttpHeaders responseHeader = new HttpHeaders();
        URI newProductURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{productId}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        responseHeader.setLocation(newProductURI);

        return new ResponseEntity<>(null,
                responseHeader,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/product/{productId}/{count}",
        produces = "application/json")
    public ResponseEntity<?> addInventory(
            @PathVariable Long count,
            @PathVariable Long productId
    )
    {
        Product p = productService.findProductById(productId);
        productService.addInventory(p.getId(), count);

        return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
    }

    @PutMapping(value= "/product/{productId}",
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct
    )
    {
        updatedProduct.setId(productId);
        productService.save(updatedProduct);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
