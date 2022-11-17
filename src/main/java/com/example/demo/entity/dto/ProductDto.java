package com.example.demo.entity.dto;

import com.example.demo.type.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

        private String name;
        private String description;
        private Double price;
        private Integer rate;
        private ProductType productType;
}
