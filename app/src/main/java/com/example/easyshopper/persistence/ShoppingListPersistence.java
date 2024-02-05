package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;

import java.util.List;

//Implementations are all currently found in ShoppingListPersistenceStub
public interface ShoppingListPersistence {

    //Returns all currently existing ShoppingList's
    List<ShoppingList> getExistingShoppingLists();

    //Returns a single ShoppingList, obtainable by its shoppingListID
    ShoppingList getShoppingListById(String id);

    //Updates the ShoppingList's information
    void updateShoppingList(ShoppingList shoppingList);

    //Adds a ShoppingList to the overall ShoppingList array
    void addShoppingList(ShoppingList shoppingList);

    //Delete a ShoppingList from the overall ShoppingList array by it's shoppingListID
    void deleteShoppingList(ShoppingList shoppingList);

    //Returns the total price of all Product's on all ShoppingList's combined
    double getAllShoppingListTotal();

    //Currently removed functions -> ProductPersistence
    //Product addProduct(Product newProduct);
    //Product deleteProduct(Product product);
    //Product deleteProductByID(int productID);
}
