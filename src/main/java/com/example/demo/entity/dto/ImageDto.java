package com.example.demo.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class ImageDto {
    private Long size;
    private String token;
    private LocalDateTime createAt;
}
