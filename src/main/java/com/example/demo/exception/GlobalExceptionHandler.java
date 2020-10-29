package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import com.example.demo.dto.Error;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handle(CommonException exception) {
        return new Error(Instant.now().toString(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(MethodArgumentNotValidException exception) {
        return new Error(Instant.now().toString(), HttpStatus.BAD_REQUEST.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
