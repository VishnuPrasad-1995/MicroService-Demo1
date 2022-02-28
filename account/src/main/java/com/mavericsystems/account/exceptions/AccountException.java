package com.mavericsystems.account.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
public class AccountException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public AccountException(String message){
        super(message);
        this.message=message;
    }

    public AccountException(HttpStatus httpStatus,String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
