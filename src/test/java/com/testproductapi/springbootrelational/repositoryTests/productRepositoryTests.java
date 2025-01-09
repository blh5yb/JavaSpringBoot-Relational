package com.testproductapi.springbootrelational.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.testproductapi.springbootrelational.entity.Product;
import com.testproductapi.springbootrelational.repository.ProductRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
public class productRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback
    public void testCreateProduct(){
        String name = "product1";
        String description = "desc1";
        int price = 2;

        Product product = Product.builder()
            .name(name).description(description).price(price)
            .build();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());
        assertEquals(name, savedProduct.getName());
        assertEquals(description, savedProduct.getDescription());
        assertEquals(price, savedProduct.getPrice());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindProductByNameFound() {
        String name = "product2";
        String description = "description2";
        int price = 3;
        Product product = Product.builder()
            .name(name).description(description).price(price)
            .build();

        productRepository.save(product);
        Product foundProduct = productRepository.findByName(name);

        assertNotNull(foundProduct);
        assertNotNull(foundProduct.getId());
        assertEquals(name, foundProduct.getName());
        assertEquals(description, foundProduct.getDescription());
        assertEquals(price, foundProduct.getPrice());

    }


    @Test
    @Transactional
    @Rollback
    public void testGetProductNotFound() {
        Product foundProduct = productRepository.findByName("nullProduct");
        assertNull(foundProduct);

    }
}
