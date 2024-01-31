package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;

import java.util.List;

public interface ShoppingListPersistence {
    List<ShoppingList> getExistingShoppingLists();

    ShoppingList getShoppingListById(int id);

    // Product addProduct(Product newProduct);

    // Product deleteProduct(Product product);

    // Product deleteProductByID(int productID);

    void updateShoppingList(int index, ShoppingList shoppingList);

    void addShoppingList(ShoppingList shoppingList);

    void deleteShoppingListById(int id);

    double getAllShoppingListTotal();

    double getShoppingListTotalById(int id);
}
