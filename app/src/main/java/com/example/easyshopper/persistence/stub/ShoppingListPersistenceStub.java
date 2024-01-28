package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.util.List;

public class ShoppingListPersistenceStub implements ShoppingListPersistence {
    @Override
    public ShoppingList getShoppingListById(int id) {
        return null;
    }

    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return null;
    }
}
