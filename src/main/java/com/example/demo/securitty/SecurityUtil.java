package com.example.demo.securitty;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

public Integer getUserId(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
   return null;
}
}
