package com.testproductapi.springbootrelational.controllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.testproductapi.springbootrelational.controller.ProductController;
import com.testproductapi.springbootrelational.dto.ProductDTO;
import com.testproductapi.springbootrelational.entity.Product;
import com.testproductapi.springbootrelational.service.ProductService;

@WebMvcTest(ProductController.class)
public class productControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    private static final String TEST_NAME = "test";
    private static final String TEST_DESCRIPTION = "description";
    private static final int TEST_PRICE = 5;
    private ProductDTO productDTO;
    @BeforeEach
    void setUp(){
        Product savedProduct = Product.builder().id((long) 17).name(TEST_NAME).description(TEST_DESCRIPTION).price(TEST_PRICE).build();
        productDTO = new ProductDTO(savedProduct);
    }

    @Test
    public void createProductClass() throws Exception {
        String productDTOJson = "{\"name\": \"test\", \"description\": \"description\", \"price\": 5}\"}";
        when(productService.createProduct(any())).thenReturn(productDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productDTOJson)
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().json(productDTOJson));
    }
}
