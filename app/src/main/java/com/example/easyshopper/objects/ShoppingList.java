package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.persistence.PricePersistence;
import java.util.UUID;

import java.util.ArrayList;

//List is created per store, products added to list
public class ShoppingList {
    PricePersistence pricePersistence = Services.getPricePersistence();
    private String shoppingListName;
    private String shoppingListID;
    private ArrayList<Product> cart;
    private Store store;

    //Constructor
    public ShoppingList(String shoppingListName, Store store){
        this.shoppingListID = UUID.randomUUID().toString();
        this.shoppingListName = shoppingListName;
        this.store = store;
        this.cart = new ArrayList<>(); //Cart represents the items in the ShoppingList
    }

    // GETTERS

    public String getShoppingListName() {
        return shoppingListName;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public Store getStore(){return store;}

    public String getShoppingListID() {return shoppingListID;}

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

    //Gives total price of the ShoppingList
    public double cartTotal(){
        double total = 0;

        for (Product i : cart){
            total += pricePersistence.getPrice(i, this.store);
        }

        //modify this double value so that it is only two decimal places long
        String formattedNumber = String.format("%.2f", total);
        total = Double.parseDouble(formattedNumber);

        return total;
    }

    @NonNull
    @Override
    public String toString() {
        return shoppingListName;
    }
}
