package com.testproductapi.springbootrelational.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.testproductapi.springbootrelational.dto.ProductDTO;
import com.testproductapi.springbootrelational.entity.Product;
import com.testproductapi.springbootrelational.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    // Convert Product entity to DTO
    private ProductDTO mapToDTO(Product product){
        System.out.println(product);
        return new ProductDTO(product);
    }

    // convert ProductDTO to Product entity
    private Product mapToEntity(ProductDTO productDTO){
        System.out.println(productDTO);
        return new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
    }

    // Get all products
    public List<ProductDTO> getAllProducts(){
        System.out.println("Get all");
        return productRepository.findAll().stream()
            .map(this::mapToDTO).collect(Collectors.toList());
    }

    // get single product by id
    public ProductDTO getProductById(Long Id) {
        System.out.println("Get by id");
        Product product = productRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        System.out.println("get by id " + product);
        return mapToDTO(product);
    }

    // create new
    public ProductDTO createProduct(ProductDTO productDTO) {
        System.out.println("create");
        Product product = mapToEntity(productDTO);
        try {
            Product savedProduct = productRepository.save(product);
            System.out.println("save " + savedProduct);
            return mapToDTO(savedProduct);
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println("type of: " + e.getCause());
            if (message.contains("Unique index or primary key violation")){
                System.out.println("exception: " + message);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid");
            } else {
                throw new RuntimeException("Error creating product with name: " + productDTO.getName() + "\n" + e);
            }
        }
        //.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request or Product Exists"));  // got id?
    }

    // update product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        System.out.println("Update: ");
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        Product updatedProduct = productRepository.save(existingProduct);
        System.out.println("Update " + existingProduct + "\n " + updatedProduct);
        return mapToDTO(updatedProduct);
    }

    // Delete product
    public void deleteProduct(Long id){
        System.out.println("Delete");
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        productRepository.delete(product);
    }
}
