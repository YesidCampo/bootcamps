package com.bootcamps.domain.exception;

public class CapabilityEntityNotCreatedException extends RuntimeException {
    public CapabilityEntityNotCreatedException(String message) {
        super(message);
    }
}