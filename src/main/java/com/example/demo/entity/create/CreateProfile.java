package com.example.demo.entity.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CreateProfile {
    @NotEmpty(message = "Name can not be empty or null")
    private String name;
    @NotBlank(message = "Email can not be empty or null")
    @Email(message = ("Please enter email"))
    private String email;
    @NotBlank(message = "Contact can not be empty or null")
    private String contact;
    @NotBlank(message = "Password can not be empty or null")
    @Size(min = 8 ,max = 240,message = ("min 8, max 240 characters"))
    private String password;
}
