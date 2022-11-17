package com.example.demo.entity;

import com.example.demo.type.ProductStatus;
import com.example.demo.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer rate;
    private LocalDateTime createAt;
    private Boolean visible;
    private ProductType productType;
    private ProductStatus productStatus;
}
