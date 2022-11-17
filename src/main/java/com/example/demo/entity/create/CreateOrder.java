package com.example.demo.entity.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateOrder {
    private String requirement;
    @NotBlank(message = "Contact can not be empty or null")
    private String contact;
    @NotBlank(message = "Address can not be empty or null")
    private String address;
    @NotBlank(message = "Product Id can not be empty or null")
    private Integer productId;
    @NotBlank(message = "Delivery Data Id can not be empty or null")
    private LocalDateTime deliveryData;
}
