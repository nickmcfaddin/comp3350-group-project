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

    //Constructor
    public ShoppingList(int shoppingListID, Store store){
        this.shoppingListID = shoppingListID;
        this.store = store;
        this.cart = new ArrayList<>(); //Cart represents the items in the ShoppingList
    }

    // GETTERS
    public Store getStore(){return store;}
    public int getShoppingListID() {
        return shoppingListID;
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
        if(!checkForProductInCart(product.getProductName())) {
            cart.add(product);
        }
    }

    //Removes Product from ShoppingList
    public void removeProductFromCart(Product product){
        if(checkForProductInCart(product.getProductName())) {
            cart.remove(product);
        }
    }

    //Gives total price of the ShoppingList
    public double cartTotal(){

        Services service = new Services();
        PricePersistence pricePersistence = service.getPricePersistence();

        double total = 0;

        for (Product i : cart){
            total += pricePersistence.getPrice(i.getProductID(), this.store.getStoreID());
        }

        return total;
    }
}
