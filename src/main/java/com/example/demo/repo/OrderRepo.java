package com.example.demo.repo;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {
}
