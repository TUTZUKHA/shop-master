package com.example.demo.controller;

import com.example.demo.entity.create.CreateOrder;
import com.example.demo.entity.dto.OrderDto;
import com.example.demo.servise.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService orderService) {
        this.service = orderService;
    }
    @PostMapping("/new")
    public ResponseEntity<?>newOrder(@RequestBody @Valid CreateOrder order){
        OrderDto result = service.create(order);
        return ResponseEntity.ok(result);
    }
}
