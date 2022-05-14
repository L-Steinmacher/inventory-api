package com.controllers;

import com.InvantoryApiTesting;
import com.exceptions.ResourceNotFoundException;
import com.models.Product;
import com.services.ProductService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = InvantoryApiTesting.class)
@AutoConfigureMockMvc
public class OrderControllerIntegrationTestWithDB {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        List<Product> productList = productService.findAll();

        for (Product p : productList)
        {
            System.out.println("Product id: "+ p.getProductid()+ "   product name: " + p.getProductname()+ "   count: "+p.getCount());
        }

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void a_listAllProducts() {
        given().when()
                .get("/products/products")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("testhat"));
    }

    @Test
    public void b_getProductById() {
        given().when()
                .get("/products/product/3")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("testcellphone"));
    }

    @Test
    public void bb_getProductByIdNotFound() {
        long productId = 20;
        given().when()
                .get("/products/product/"+productId)
                .then()
                .statusCode(500);
    }
}
