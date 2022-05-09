package com.services;

import com.InvantoryApiTesting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvantoryApiTesting.class,
    properties = {"command.line.runner.enabled=false"})
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;

    @Before
    public void setUp() throws Exception
    {
        System.out.println("Start Setup !!!!");
    }

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public void a_findProductById()
    {
        return;
    }
}
