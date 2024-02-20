package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

//List is created per store, products added to list
public class ShoppingList implements Serializable {
    private ArrayList<Product> cart;
    private Store store;

    //Constructor
    public ShoppingList(Store store){
        this.store = store;
        this.cart = new ArrayList<>(); //Cart represents the items in the ShoppingList
    }

    // GETTERS

    public String getShoppingListName() {
        return store.getStoreName();
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public Store getStore(){return store;}

    public int getShoppingListID() {return store.getStoreID();}

    public ArrayList<Product> getItemList(){
        return cart;
    }

    //Check if the Product is in this ShoppingList
    public boolean checkForProductInCart (Product product) {
        for(Product i : cart){
            if(i.getProductID() == product.getProductID())
                return true;
        }

        return false;
    }

    //Adds Product to the ShoppingList
    public void addProductToCart(Product product){
        if(!checkForProductInCart(product)) {
            cart.add(product);
        }
    }

    //Removes Product from ShoppingList
    public void removeProductFromCart(Product product){
        if(checkForProductInCart(product)) {
            cart.remove(product);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return store.getStoreName();
    }
}
