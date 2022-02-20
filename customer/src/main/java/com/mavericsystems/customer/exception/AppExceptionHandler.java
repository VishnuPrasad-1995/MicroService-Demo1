package com.mavericsystems.customer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    ResponseEntity<Object> customerNotFoundHandler(Exception exception, ServletWebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setErrors(Arrays.asList(exception.getMessage()));
        apiError.setPath(request.getDescription(false));
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    ResponseEntity<Object>  customerAlreadyExistHandler(Exception exception, ServletWebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.CONFLICT);
        apiError.setErrors(Arrays.asList(exception.getMessage()));
        apiError.setPath(request.getDescription(false));
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CustomFeignException.class)
    ResponseEntity<Object>  hystrixFallBackHandler(Exception exception, ServletWebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        apiError.setErrors(Arrays.asList(exception.getMessage()));
        apiError.setPath(request.getDescription(false));
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        {
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(err -> err.getField() + " : " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            ApiError apiError = new ApiError();
            apiError.setStatus(HttpStatus.NOT_FOUND);
            apiError.setErrors(errors);
            apiError.setPath(request.getDescription(false));
            apiError.setTimestamp(LocalDateTime.now());
            return new ResponseEntity<>(apiError, headers, apiError.getStatus());
        }

    }
}
