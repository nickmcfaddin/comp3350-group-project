package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.ShoppingList;

import java.util.List;

public interface ShoppingListPersistence {
    ShoppingList getShoppingListById(int id);
    List<ShoppingList> getExistingShoppingLists();
}
