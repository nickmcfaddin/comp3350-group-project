package com.example.easyshopper.objects;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;

import java.util.ArrayList;

public class ShoppingList {
    private int shoppingListID;
    private ArrayList<Product> cart;
    private Store store;
    private double totalAmount;

    public ShoppingList(int shoppingListID, Store store){
        this.shoppingListID = shoppingListID;
        this.store = store;
        this.cart = new ArrayList<>();
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

    //Check if the product is in this shopping list's cart
    public boolean checkForProductInCart (String productName) {
        for(Product i : cart){
            if(i.getProductName().equals(productName))
                return true;
        }
        return false;
    }

    public void addProductToCart(Product product){

        Services service = new Services();
        PricePersistence pricePersistence = service.getPricePersistence();

        if(!checkForProductInCart(product.getProductName())) {
            cart.add(product);
            totalAmount += pricePersistence.getPrice(product.getProductID(), this.store.getStoreID());
        }
    }
    
    public void removeProductFromCart(Product product){
        Services service = new Services();
        PricePersistence pricePersistence = service.getPricePersistence();

        if(checkForProductInCart(product.getProductName())) {
            cart.remove(product);
            totalAmount -= pricePersistence.getPrice(product.getProductID(), this.store.getStoreID());
        }
    }

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
