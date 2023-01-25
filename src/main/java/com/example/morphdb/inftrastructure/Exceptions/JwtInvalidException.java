package com.example.morphdb.inftrastructure.Exceptions;

public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}
