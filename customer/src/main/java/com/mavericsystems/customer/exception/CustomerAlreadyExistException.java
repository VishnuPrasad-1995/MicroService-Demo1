package com.mavericsystems.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Customer Already Exist")
public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(String s) {
        super(s);
    }
}
