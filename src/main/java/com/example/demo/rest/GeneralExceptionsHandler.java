package com.example.demo.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
@Slf4j
public class GeneralExceptionsHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(ConstraintViolationException e) {
        log.warn("Constraint validation exception: {}", e.getMessage());
        return ResponseEntity.badRequest().body(String.format("Constraint validation exception: %s", e.getMessage()));
    }
}
