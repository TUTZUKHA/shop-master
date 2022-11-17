package com.example.demo.excaption;

public class EmailNotDelivered extends RuntimeException{
public EmailNotDelivered(String message){
    super(message);
}
}
