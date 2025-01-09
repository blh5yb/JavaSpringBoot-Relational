package com.testproductapi.springbootrelational.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testproductapi.springbootrelational.dto.ProductDTO;
import com.testproductapi.springbootrelational.entity.Product;
import com.testproductapi.springbootrelational.repository.ProductRepository;
import com.testproductapi.springbootrelational.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private static final String TEST_NAME = "test";
    private static final String TEST_DESCRIPTION = "description";
    private static final int TEST_PRICE = 5;
    private ProductDTO productDTO;
    private Product savedProduct;

    @BeforeEach
    void setUp(){
        //product = Product.builder()..name(TEST_NAME).description(TEST_DESCRIPTION).price(TEST_PRICE).build();
        savedProduct = Product.builder().id((long) 17).name(TEST_NAME).description(TEST_DESCRIPTION).price(TEST_PRICE).build();
        productDTO = new ProductDTO(savedProduct);
    }

    //@AfterEach
    //public void tearDown() {
    //    // Reset mocks or any shared state if necessary
    //    Mockito.reset(MockMvc)
    //}

    @Test
    public void registerProductSuccess(){
        System.err.println("product" + productDTO);
        //when(productRepository.findByName(productDTO.getName())).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
//
        ProductDTO foundProduct = productService.createProduct(productDTO);
        //verify(productRepository, times(1)).save(savedProduct);
        assertEquals(foundProduct.getId(), savedProduct.getId());
        assertEquals(foundProduct.getName(), savedProduct.getName());
    }
}
