package com.mavericsystems.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Account Type Already Exist")
public class AccountTypeAlreadyExist extends RuntimeException{
    public AccountTypeAlreadyExist(String s) {
        super(s);
    }
}
