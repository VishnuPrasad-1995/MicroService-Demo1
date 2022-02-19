package com.mavericsystems.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Account cannot be deleted for a single account holder without permission")
public class AccountCannotBeDeletedException extends RuntimeException{
    public AccountCannotBeDeletedException(String s) {
        super(s);
    }
}
