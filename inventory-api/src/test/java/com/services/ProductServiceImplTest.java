package com.services;

import com.InvantoryApiTesting;
import com.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Product;
import com.repository.ProductsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvantoryApiTesting.class,
    properties = {"command.line.runner.enabled=false"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;

    @MockBean
    ProductsRepository productsRepo;

    ArrayList productList = new ArrayList<Product>();
    @Before
    public void setUp() throws Exception
    {
        System.out.println("Start Setup !!!!");

        productList = new ArrayList<>();

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
        p1.setCount(100);
        p2.setCount(200);
        p3.setCount(300);


        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public void a_findProductById()
    {
        Mockito.when(productsRepo.findById(1L))
                .thenReturn(Optional.of((Product) productList.get(0)));
        assertEquals("hat", productService.findProductById(1).getProductname());
        assertEquals(100, productService.findProductById(1).getCount());
        assertNotEquals("clipboard", productService.findProductById(1).getProductname());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void aa_notFindProductById()
    {
        Mockito.when(productsRepo.findById(4L))
                .thenThrow(ResourceNotFoundException.class);
        assertEquals("clipboard", productService.findProductById(4));
    }


    @Test
    public void findAll() {
        Mockito.when(productsRepo.findAll())
                .thenReturn(productList);
        assertEquals(3,productService.findAll().size());
    }


    @Test
    public void b_delete() {
        Mockito.when(productsRepo.findById(1L))
                .thenReturn(Optional.of((Product) productList.get(0)));
        Mockito.doNothing()
                .when(productsRepo)
                .deleteById(1L);
        productService.delete(1);
        assertEquals(3, productList.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void bb_notDeleteIdNotFound() {
        Mockito.when(productsRepo.findById(4L))
                .thenThrow(ResourceNotFoundException.class);
        Mockito.doNothing()
                .when(productsRepo)
                .deleteById(4L);
        productService.delete(4L);
        assertEquals(3,productList.size());
    }

    @Test
    public void c_save() {
        Product p4 = new Product(
                "mug",
                "put a drink in it"
        );
        p4.setCount(400);

        Mockito.when(productsRepo.save(any(Product.class)))
                .thenReturn(p4);
        Product newProduct = productService.save(p4);
        assertNotNull(newProduct);
        assertEquals(p4, newProduct);
    }

    @Test
    public void d_update() throws Exception {
        String p2NewName = "godzilla";
        String p2NewDiscription = "wrecks stuff";

        Product p2 = new Product(p2NewName,p2NewDiscription);
        p2.setId(2L);

        ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Product p4 = objectMapper
                .readValue(objectMapper.writeValueAsString(p2),
                        Product.class);

        Mockito.when(productsRepo.findById(2L))
                .thenReturn(Optional.of(p4));
        Mockito.when(productsRepo.save(any(Product.class)))
                .thenReturn(p2);

        Product addProduct = productService.update(p2, 2);

        assertNotNull(addProduct);
        assertEquals(p2.getProductname(), addProduct.getProductname());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void dd_notUpdateIdNotFound() {
    }

    @Test
    public void e_addInventory() {
        long addInv = 300;
        Mockito.when(productsRepo.findById(1l))
                .thenReturn(Optional.of((Product)productList.get(0)));
        assertEquals(100, productService.findProductById(1).getCount());
        assertEquals(400, productService.addInventory(1, addInv).getCount());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void ee_notAddInventoryIdNotFound() {
        Mockito.when(productsRepo.findById(1234L))
                .thenThrow(ResourceNotFoundException.class);
        assertEquals("hat",
                productService.findProductById(1234).getProductname());
    }

    @Test
    public void deleteAll() {

    }
}
