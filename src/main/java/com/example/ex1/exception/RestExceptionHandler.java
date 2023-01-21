package com.example.ex1.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handle(Exception exception) {
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }
}
