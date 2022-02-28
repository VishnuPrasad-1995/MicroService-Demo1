package com.mavericsystems.customer.aop;

import com.mavericsystems.customer.exception.CustomerException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@Aspect
@Configuration
public class CustomerAspect {
    private final Logger log = LoggerFactory.getLogger(CustomerAspect.class);

    @Before(value = "execution(* com.mavericsystems.customer.controller.*.*(..))")
    public void logStatementBefore(JoinPoint joinPoint) {
        log.info("Executing controller {}", joinPoint);
    }

    @Before(value = "execution(* com.mavericsystems.customer.service.*.*(..))")
    public void logStatementBeforeService(JoinPoint joinPoint) {
        log.info("Executing service {}", joinPoint);
    }

    @Before(value = "execution(* getAllCustomer(..))")
    public void logStatementBeforeCon(JoinPoint joinPoint) {
        log.info("Executing controller{}", joinPoint);
    }

    @After(value = "execution(* com.mavericsystems.customer.controller.*.*(..))")
    public void logStatementAfter(JoinPoint joinPoint) {
        log.info("Complete exceution of controller {}", joinPoint);
    }

    @AfterReturning(value = "execution(* com.mavericsystems.customer.controller.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("{} returned with value {}", joinPoint, result);
    }

    @Around(value = "execution(* com.mavericsystems.customer.service.*.*(..))")
    public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            return joinPoint.proceed();

        } catch (CustomerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Around(value = "execution(* com.mavericsystems.customer.controller.*.*(..))")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        try {
            Object obj = joinPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;
            log.info("Time taken by {} is {} ms", joinPoint, timeTaken);
            return obj;
        } catch (CustomerException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }


}
