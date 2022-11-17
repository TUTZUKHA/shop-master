package com.example.demo.entity.dto;

import com.example.demo.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class OrderDto {
    private Integer id;
    private LocalDateTime deliveryData;
    private String requirement;
    private String contact;
    private OrderStatus order_status;
    private String address;
    private LocalDateTime createAt;
}
