package com.example.demo.excaption;

public class BadRequest extends RuntimeException{
    public BadRequest(String message){
        super(message);
    }
}
