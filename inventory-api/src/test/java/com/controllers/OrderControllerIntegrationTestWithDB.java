package com.controllers;

import com.InvantoryApiTesting;
import com.exceptions.ResourceNotFoundException;
import com.models.Order;
import com.models.OrderDetails;
import com.models.Product;
import com.services.OrderDetailsService;
import com.services.OrderService;
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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.get;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        List<Product> productList = productService.findAll();
        List<Order> orderList = orderService.findAll();

        for (Product p : productList)
        {
            System.out.println("Product id: "+ p.getProductid()+ "   product name: " + p.getProductname()+ "   count: "+p.getCount());
        }

        for (Order o : orderList) {
            System.out.println("Order id: " + o.getOrderid()+ " Customerid: "+o.getCustomerid());
        }

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void a_listAllOrders() {
        given().when()
                .get("/orders/orders")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("will pick up whenever"));
    }

    @Test
    public void b_getOrderById() {
        String returnJson = "{\"orderid\":5,\"customerid\":42,\"comments\":\"will pick up whenever\",\"products\":[{\"product\":{\"productid\":1,\"productname\":\"testhat\",\"count\":90,\"discription\" \"put it on ya head\"},\"quantity\":10}]}";

        given().when()
                .get("/orders/order/5")
                .then()
                .statusCode(200)
                .and()
                .body(containsString(returnJson));
    }

}
