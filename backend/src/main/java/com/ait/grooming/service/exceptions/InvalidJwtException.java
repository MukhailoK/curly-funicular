package com.ait.grooming.service.exceptions;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message) {
        super(message);
    }
}
