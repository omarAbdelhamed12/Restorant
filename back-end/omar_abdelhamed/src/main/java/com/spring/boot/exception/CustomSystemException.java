package com.spring.boot.exception;

public class CustomSystemException extends RuntimeException {
    public CustomSystemException(String message) {
        super(message);
    }
}
