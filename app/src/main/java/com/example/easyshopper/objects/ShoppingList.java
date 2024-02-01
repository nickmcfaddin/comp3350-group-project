package com.example.easyshopper.objects;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;

import java.util.ArrayList;

//List is created per store, products added to list
public class ShoppingList {
    private int shoppingListID;
    private ArrayList<Product> cart;
    private Store store;
    private double totalAmount;

    //Constructor
    public ShoppingList(int shoppingListID, Store store){
        this.shoppingListID = shoppingListID;
        this.store = store;
        this.cart = new ArrayList<>(); //Cart represents the items in the ShoppingList
        this.totalAmount = 0;
    }

    // GETTERS
    public Store getStore(){return store;}
    public int getShoppingListID() {
        return shoppingListID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isEmpty(){
        return cart.size() == 0;
    }

    public ArrayList<Product> getItemList(){
        return cart;
    }

    //Check if the Product is in this ShoppingList
    public boolean checkForProductInCart (String productName) {
        for(Product i : cart){
            if(i.getProductName().equals(productName))
                return true;
        }
        return false;
    }

    //Adds Product to the ShoppingList
    public void addProductToCart(Product product){

        Services service = new Services();
        PricePersistence pricePersistence = service.getPricePersistence();

        if(!checkForProductInCart(product.getProductName())) {
            cart.add(product);
            totalAmount += pricePersistence.getPrice(product.getProductID(), this.store.getStoreID());
        }
    }

    //Removes Product from ShoppingList
    public void removeProductFromCart(Product product){
        Services service = new Services();
        PricePersistence pricePersistence = service.getPricePersistence();

        if(checkForProductInCart(product.getProductName())) {
            cart.remove(product);
            totalAmount -= pricePersistence.getPrice(product.getProductID(), this.store.getStoreID());
        }
    }

    //Gives total price of the ShoppingList
    public void cartTotal(){

        Services service = new Services();
        PricePersistence pricePersistence = service.getPricePersistence();

        double total = 0;

        for (Product i : cart){
            total += pricePersistence.getPrice(i.getProductID(), this.store.getStoreID());
        }

        totalAmount = total;
    }
}
