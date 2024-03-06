package com.example.easyshopper.objects;

public interface ProductListVisitor {
    void add(ShoppingList shoppingList);
    void add(RequestList requestList);
    void delete(ShoppingList shoppingList);
    void delete(RequestList requestList);
}
