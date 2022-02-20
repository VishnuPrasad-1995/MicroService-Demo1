package com.mavericsystems.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFound(HttpServletRequest request, AccountNotFoundException ex){
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountTypeAlreadyExist.class)
    public ResponseEntity<Object> handleAccountAlreadyExist(HttpServletRequest request, AccountTypeAlreadyExist ex){
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountCannotBeDeletedException.class)
    public ResponseEntity<Object> handleAccountCannotBeDeleted(HttpServletRequest request, AccountCannotBeDeletedException ex){
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()), HttpStatus.NOT_ACCEPTABLE);
    }
}
