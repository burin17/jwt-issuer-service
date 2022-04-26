package com.gmail.burinigor7.jwtissuerservice.advice;

import com.gmail.burinigor7.jwtissuerservice.exception.ErrorResponse;
import com.gmail.burinigor7.jwtissuerservice.exception.UnauthorisedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorisedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorisedException e) {
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }
}