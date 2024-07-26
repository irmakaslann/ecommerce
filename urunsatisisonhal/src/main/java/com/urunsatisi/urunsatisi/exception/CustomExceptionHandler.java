package com.urunsatisi.urunsatisi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomerNotNullException.class)
    public ResponseEntity<ErrorResponse> customerNotNull(CustomerNotNullException customerNotNullException) {
        List<String> details = new ArrayList<>();
        details.add(customerNotNullException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Customer must not be null", details);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> customerNotFound(CustomerNotFoundException customerNotFoundException) {
        List<String> details = new ArrayList<>();
        details.add(customerNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Customer not found", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public static class ErrorResponse {
        private String message;
        private List<String> details;

        public ErrorResponse(String message, List<String> details) {
            this.message = message;
            this.details = details;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getDetails() {
            return details;
        }
    }
}
