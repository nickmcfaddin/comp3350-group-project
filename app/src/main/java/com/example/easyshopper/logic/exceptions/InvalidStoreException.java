package com.example.easyshopper.logic.exceptions;

public class InvalidStoreException extends LogicException {
    public InvalidStoreException(String error) {
        super("Invalid store: " + error);
    }
}
