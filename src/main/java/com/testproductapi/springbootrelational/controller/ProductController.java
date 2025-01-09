package com.testproductapi.springbootrelational.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testproductapi.springbootrelational.dto.ProductDTO;
import com.testproductapi.springbootrelational.entity.Product;
import com.testproductapi.springbootrelational.service.ProductService;

//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired //DI
    private ProductService productService;

    // Get all products
    @GetMapping
    //@ApiOperation(value = "Get Products", notes = "This endpoint returns all products in the PRODUCT table")
    @Operation(summary = "Get All Products", description = "This endpoint returns all products in the PRODUCT table")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved products",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
        ))
    })
    @Tag(name="1. Get All Products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get product by id
    @GetMapping("/{id}")
    //@ApiOperation(value = "Get Product By Id", notes = "This endpoint return product with the id found in the path variable")
    @Operation(summary = "Get A Product By Id", description = "This endpoint return product with the id found in the path variable")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved products",
        content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", description = "The query product id is not found in the db",
        content = @Content)
    })
    @Tag(name="2. Get A Product By Id")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Create product
    @PostMapping
    //@ApiOperation(value = "Create New Product", notes = "This endpoint creates a new product.\nNote: Name must be unique")
    @Operation(summary = "Create New Product", description = "This endpoint creates a new product.\nNote: Name must be unique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created a new product",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "400", description = "Failed attempt to create new product with non-unique name", 
        content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server runtime error", 
        content = @Content),
    })
    @Tag(name="3. Create A Product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Update a product
    @PutMapping("/{id}")
    //@ApiOperation(value = "Update A Product", notes = "This endpoint updates a product with the given id from the path variable")
    @Operation(summary = "Update A Product", description = "This endpoint updates a product with the given id from the path variable")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the product",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", description = "The query product id is not found in the db",
        content = @Content)
    })
    @Tag(name="4. Update A Product By Id")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete A Product", notes = "This endpoint deletes a product with the given id from the path variable")
    @Operation(summary = "Delete A Product", description = "This endpoint deletes a product with the given id from the path variable")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete operation was successful", content = @Content),
        @ApiResponse(responseCode = "404", description = "The query product id was not found in the db", content = @Content)
    })
    @Tag(name="5. Delete A Product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // makes sure item is deleted
    }
}
