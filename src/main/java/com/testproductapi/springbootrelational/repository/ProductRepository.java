package com.testproductapi.springbootrelational.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testproductapi.springbootrelational.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Method signature to find a product name
    Product findByName(String name);

}