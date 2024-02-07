package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;
import java.util.UUID;

import java.util.ArrayList;

//List is created per store, products added to list
public class ShoppingList {
    PricePersistence pricePersistence = Services.getPricePersistence();
    private String shoppingListID;
    private ArrayList<Product> cart;
    private Store store;

    //Constructor
    public ShoppingList(Store store){
        this.shoppingListID = UUID.randomUUID().toString();
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

    public String getShoppingListID() {return shoppingListID;}

    public boolean isEmpty(){
        return cart.size() == 0;
    }

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
