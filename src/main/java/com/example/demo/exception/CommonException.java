package com.example.demo.exception;

// TODO GTB-工程实践: - 异常名起的不太好，可以使用EntityNotFoundException
public class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }
}
