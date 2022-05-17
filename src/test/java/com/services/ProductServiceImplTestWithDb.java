package com.services;

import com.InvantoryApiTesting;
import com.models.Product;
import com.repository.ProductsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvantoryApiTesting.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceImplTestWithDb {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductsRepository productsRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        List<Product> productList = productService.findAll();
        for (Product p: productList)
        {
            System.out.println("Product id: "+ p.getProductid()+ "   product name: " + p.getProductname()+ "   count: "+p.getCount());
        }
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void whenContextLoads_thenServiceISNotNull() {
        assertNotNull(productService.findAll());
    }



}
