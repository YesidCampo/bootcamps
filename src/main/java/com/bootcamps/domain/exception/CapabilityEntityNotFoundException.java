package com.bootcamps.domain.exception;

public class CapabilityEntityNotFoundException extends RuntimeException {
    public CapabilityEntityNotFoundException(String message) {
        super(message);
    }
}