package com.mavericsystems.customer.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class CustomerException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public CustomerException(String message){
        super(message);
        this.message=message;
    }

    public CustomerException(HttpStatus httpStatus,String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
