package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ShoppingListPersistence;


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

    private ShoppingListPersistence shoppingListPersistence = Services.getShoppingListPersistence();;

    //construct
    public ShoppingListHandler(){}

    public String getShoppingListByName(){
        return null;
    }

    //add item into the given shopping list by id
    public void addItemToList(Product newProduct, int shoppingListId){
        ShoppingList list = shoppingListPersistence.getShoppingListById(shoppingListId);

        list.addProductToCart(newProduct);
    }

    //!!
    //create a new shopping list and add to overall shopping list array
    //idk How shoppingListId work, does it will be gaven as parameter with this function?
    public void createShoppingList(int shoppingListId, Store store){
        ShoppingList newList = new ShoppingList(shoppingListId, store);

        shoppingListPersistence.addShoppingList(newList);
    }

    //remove given shoppingList from overall shopping list array
    public void removeShoppingList(int shoppingListId){
        shoppingListPersistence.deleteShoppingListById(shoppingListId);
    }

    //remove an item from a shopping list
    public void removeProduct(Product product, int shoppingListId){
        ShoppingList list = shoppingListPersistence.getShoppingListById(shoppingListId);

        list.removeProductFromCart(product);
    }

    //Does a list contain an item
    public boolean checkItemExistInList(Product product, int shoppingListId){
        ShoppingList list = shoppingListPersistence.getShoppingListById(shoppingListId);
        return list.checkForProductInCart(product.getProductID());
    }
}
