package com.services;

import com.InvantoryApiTesting;
import com.models.Product;
import com.repository.ProductsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvantoryApiTesting.class,
    properties = {"command.line.runner.enabled=false"})
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

//        productService.addInventory(1, 100);
//        productService.addInventory(2, 200);
//        productService.addInventory(3,300);
//
//        productList.add(p1);
//        productList.add(p2);
//        productList.add(p3);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public void a_findProductById()
    {
//        Mockito.when(productsRepo.findById(1L))
//                .thenReturn(Optional.of((Product) productList.get(0)));
//        assertEquals("hat", productService.findProductById(1).getProductname());
    }


    @Test
    public void findAll() {
    }


    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void addInventory() {
    }

    @Test
    public void deleteAll() {
    }
}
