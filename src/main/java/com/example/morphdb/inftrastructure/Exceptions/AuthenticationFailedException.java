package com.example.morphdb.inftrastructure.Exceptions;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
