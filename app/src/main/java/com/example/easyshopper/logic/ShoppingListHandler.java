package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.util.List;


//Handles the shopping lists
public class ShoppingListHandler {

    /*
    *Methods to implement
    *   Get shopping list by name
    *   Add an item to a shopping list
    *   Creating a shopping list
    *   Deleting a shopping list
    *   Removing an item from a shopping list
    *   Does a list contain a certain item
     */

    private ShoppingListPersistence shoppingListPersistence = Services.getShoppingListPersistence();

    //construct
    public ShoppingListHandler(){}

    public String getShoppingListByName(){
        return null;
    }

    public List<ShoppingList> getAllShoppingLists(){
        return shoppingListPersistence.getExistingShoppingLists();
    }

    //add item into the given shopping list
    public void addItemToList(Product newProduct, ShoppingList shoppingList){
        shoppingList.addProductToCart(newProduct);
        shoppingListPersistence.updateShoppingList(shoppingList);
    }

    //!!
    //create a new shopping list and add to overall shopping list array
    //idk How shoppingListId work, does it will be gaven as parameter with this function?
    public void createShoppingList(ShoppingList newList){
        shoppingListPersistence.addShoppingList(newList);
    }

    //remove given shoppingList from overall shopping list array
    public void removeShoppingList(ShoppingList shoppingList){
        shoppingListPersistence.deleteShoppingList(shoppingList);
    }

    //remove an item from a shopping list
    public void removeProduct(Product product, ShoppingList shoppingList){
        shoppingList.removeProductFromCart(product);
        shoppingListPersistence.updateShoppingList(shoppingList);
    }
}
