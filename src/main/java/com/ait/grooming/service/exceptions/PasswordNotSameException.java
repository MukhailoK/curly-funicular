package com.ait.grooming.service.exceptions;

public class PasswordNotSameException extends RuntimeException{
    public PasswordNotSameException(String message){
        super(message);
    }
}
