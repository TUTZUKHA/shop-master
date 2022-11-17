package com.example.demo.entity.create;

import com.example.demo.type.ProductStatus;
import com.example.demo.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class CreateProduct {
    @NotBlank(message = "Name can not be empty or null")
    private String name;
    @NotBlank(message = "description can not be empty or null")
    private String description;
    @NotBlank(message = "price can not be empty or null")
    private Double price;
    @NotBlank(message = "productType can not be empty or null")
    private ProductType productType;
    @NotBlank(message = "productStatus can not be empty or null")
    private ProductStatus productStatus;
}
