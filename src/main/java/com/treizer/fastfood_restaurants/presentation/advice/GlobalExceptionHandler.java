package com.treizer.fastfood_restaurants.presentation.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getRejectedValue() + ", " + e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("NOT FOUND", ex.getMessage()));
    }

    // HttpMessageNotReadableException: To handle cases where the request body is
    // not readable
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        // return ResponseEntity.badRequest().body(Map.of("BAD REQUEST",
        // ex.getMessage()));
        return ResponseEntity.badRequest().body(Map.of("BAD REQUEST", "The request body is not readable"));
    }

    // ConstraintViolationException: To handle cases where a constraint on the
    // database is violated.
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(Map.of("BAD REQUEST", ex.getMessage()));
    }

    // General Exceptions

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("BAD REQUEST", ex.getMessage()));
    }

    // Exception: To handle any other exceptions that might occur.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(Map.of("An unexpected error ocurred: ", ex.getMessage()));
    }

}
