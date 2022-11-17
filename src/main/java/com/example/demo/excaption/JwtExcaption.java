package com.example.demo.excaption;

public class JwtExcaption extends RuntimeException{
    public JwtExcaption (String message){
        super(message);
    }
}
