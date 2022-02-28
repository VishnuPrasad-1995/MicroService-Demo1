package com.mavericsystems.customer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Customer Already Exist")
public class CustomFeignException extends RuntimeException{
    public CustomFeignException(String s) {
        super(s);
    }
}
