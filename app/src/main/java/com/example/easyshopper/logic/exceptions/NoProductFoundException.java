package com.example.easyshopper.logic.exceptions;

public class NoProductFoundException extends RuntimeException{
    public NoProductFoundException(String error) {
        super(error);
    }
}
