package com.example.morphdb.inftrastructure.Exceptions;

public class CustomNotFoundException extends  RuntimeException{
    public CustomNotFoundException(String message) {
        super(message);
    }
}
