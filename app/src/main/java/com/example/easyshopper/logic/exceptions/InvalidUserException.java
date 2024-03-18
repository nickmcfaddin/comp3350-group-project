package com.example.easyshopper.logic.exceptions;

public class InvalidUserException extends LogicException {
    public InvalidUserException (String error) {
        super("Invalid User: " + error);
    }
}
