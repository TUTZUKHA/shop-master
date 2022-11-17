package com.example.demo.entity.update;

import com.example.demo.type.ProductStatus;
import com.example.demo.type.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProduct {
        private String name;
        private String description;
        private Double price;
        private Integer rate;
        private Boolean visible;
        private ProductType productType;
        private ProductStatus productStatus;
}
