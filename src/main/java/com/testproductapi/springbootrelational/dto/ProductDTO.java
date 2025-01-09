package com.testproductapi.springbootrelational.dto;

import com.testproductapi.springbootrelational.entity.Product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//import lombok.Builder;
import lombok.Data;

@Data

//@Builder
public class ProductDTO {
    
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is required!")
    @Size(min = 3, message = "Name must have at least 3 characters!")
    @Size(max = 255, message = "Name can have at most 255 characters!")
    private String name;

    @NotBlank(message = "Description is required!")
    @Size(min = 3, message = "Name must have at least 3 characters!")
    @Size(max = 255, message = "Name can have at most 255 characters!")
    private String description;

    @NotBlank(message = "price is required!")
    //@Size(min = 3, message = "Name must have at least 3 characters!")
    //@Size(max = 255, message = "Name can have at most 255 characters!")
    private double price;

    public ProductDTO(){}

    public ProductDTO(Product Product) {
        this.id = Product.getId();
        this.name = Product.getName();
        this.description = Product.getDescription();
        this.price = Product.getPrice();
    }

    // Getters and Setters

    //getters, setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }
}
