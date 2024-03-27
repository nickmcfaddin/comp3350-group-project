package com.example.easyshopper.logic.exceptions;

public class NoProductRequestException extends RuntimeException{
    public NoProductRequestException (String error) {
        super(error);
    }
}
