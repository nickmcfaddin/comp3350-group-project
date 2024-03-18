package com.example.easyshopper.logic.exceptions;

public class InvalidRequestListException extends LogicException {
    public InvalidRequestListException(String error) {
        super("Invalid Request List: " + error);
    }
}
