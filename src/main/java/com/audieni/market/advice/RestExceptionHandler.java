package com.audieni.market.advice;

import com.audieni.market.exceptions.NotLoggedInException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<Object> notLoggedInException(HttpServletRequest request,
                                                       NotLoggedInException notLoggedInException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in.");
    }
}
