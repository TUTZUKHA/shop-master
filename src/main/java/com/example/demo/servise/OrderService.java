package com.example.demo.servise;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.create.CreateOrder;
import com.example.demo.entity.dto.OrderDto;
import com.example.demo.excaption.BadRequest;
import com.example.demo.repo.OrderRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.type.OrderStatus;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public OrderService(
            OrderRepo orderRepo,
            ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public OrderDto create(CreateOrder dto) {
        Optional<Product>optionalProduct = productRepo.findById(dto.getProductId());
        if (optionalProduct.isEmpty()){
            throw new BadRequest("Product not found");
        }
        Order order = new Order();
        //todo setUserId
        order.setProductId(dto.getProductId());
        order.setDeliveryData(dto.getDeliveryData());
        order.setRequirement(dto.getRequirement());
        order.setContact(dto.getContact());
        order.setOrder_status(OrderStatus.CREATED);
        order.setAddress(dto.getAddress());
        order.setCreateAt(LocalDateTime.now());
        orderRepo.save(order);
        return getDto(order);
    }

    private OrderDto getDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setDeliveryData(order.getDeliveryData());
        dto.setRequirement(order.getRequirement());
        dto.setContact(order.getContact());
        dto.setOrder_status(order.getOrder_status());
        dto.setAddress(order.getAddress());
        dto.setCreateAt(order.getCreateAt());
        return dto;
    }
}
