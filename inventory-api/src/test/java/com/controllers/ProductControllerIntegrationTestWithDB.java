package com.controllers;

import com.InvantoryApiTesting;
import com.exceptions.ResourceNotFoundException;
import com.models.Product;
import com.services.ProductService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = InvantoryApiTesting.class)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTestWithDB {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    ProductService productService;

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
    public void bb_getProductByIdNotFound()
            throws Exception {
        String exeptionParam = "Not Found";
        given().when()
                .get("/products/product/20")
                .then()
                .statusCode(404);
    }

    @Test
    public void c_deleteProductByIdNotInOrder()
    {
        long aProduct = 4;
        given().when()
                .delete("/products/product/"+aProduct)
                .then()
                .statusCode(200);
    }

    @Test
    public void cc_deleteProductByIdNotFound()
    {
        long aProduct = 22;
        given().when()
                .delete("/products/product/"+aProduct)
                .then()
                .statusCode(404);
    }

    /**
     * This test will fail till exception is written in ProductServiceImpl
     */
    @Test
    public void ccc_deleteProductByIdInOrder()
    {
        long aProduct = 1;
        given().when()
                .delete("/products/product/"+aProduct)
                .then()
                .statusCode(500);
    }

    @Test
    public void d_addNewProduct() throws Exception
    {
        String jsonInput = "{\n" +
                "    \"productname\": \"pebbles\",\n" +
                "    \"discription\": \"write with them\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products/product")
                .content(jsonInput)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                ;
    }

    @Test
    public void e_addInventory() throws Exception
    {
        long aProduct = 1;
        long quantity = 200;

        given().when()
                .put("/products/product/"+aProduct+"/"+quantity)
                .then()
                .statusCode(202)
                .and()
                .body(containsString("\"count\":290"));
    }

    @Test
    public void ee_addInvantoryIdNotFound() throws Exception
    {
        long aProduct = 22;
        given().when()
                .delete("/products/product/"+aProduct)
                .then()
                .statusCode(404);
    }

}
