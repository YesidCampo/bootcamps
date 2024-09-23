package com.bootcamps.domain.exception;

public class InvalidCapabilityException extends RuntimeException {
    public InvalidCapabilityException(String message) {
        super(message);
    }
}