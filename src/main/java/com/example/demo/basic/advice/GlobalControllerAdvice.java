package com.example.demo.basic.advice;

import com.example.demo.basic.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.demo.basic.controller")
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        System.out.println(e.getClass().getName());
        System.out.println("---------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("---------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(Exception e) {


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity authException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
