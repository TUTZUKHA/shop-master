package com.example.demo.repo;

import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image,Integer> {
    Optional<Image> findByToken (String token);
}
