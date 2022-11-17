package com.example.demo.entity;

import com.example.demo.type.OrderStatus;
import com.example.demo.type.PaymentType;
import com.example.demo.type.ProductStatus;
import com.example.demo.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("orders"))
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer profilId;
    private Integer productId;
    private LocalDateTime deliveryData;
    private String requirement;
    private String contact;
    private PaymentType payment_type;
    private OrderStatus order_status;
    private String address;
    private LocalDateTime createAt;
    private LocalDateTime deliveredAt;
}
