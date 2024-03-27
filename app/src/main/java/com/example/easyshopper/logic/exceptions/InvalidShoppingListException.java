package com.example.easyshopper.logic.exceptions;

public class InvalidShoppingListException extends LogicException {
    public InvalidShoppingListException(String error) {
        super("Invalid store: " + error);
    }
}
