package com.urunsatisi.urunsatisi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomerNotNullException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotNullException customerNotNullException) {
        ErrorResponse errorResponse = new ErrorResponse("Customer must not be null", customerNotNullException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(SourceNotFoundException sourceNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse("Source not found", sourceNotFoundException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
