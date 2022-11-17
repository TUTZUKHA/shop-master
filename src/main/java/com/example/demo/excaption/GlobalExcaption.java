package com.example.demo.excaption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcaption {
    @ExceptionHandler
    public ResponseEntity<?>hundler(BadRequest excaption){
        return ResponseEntity.badRequest().body(excaption.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<?>hundler(EmailNotDelivered excaption){
        return ResponseEntity.badRequest().body(excaption.getMessage());
    }
}
