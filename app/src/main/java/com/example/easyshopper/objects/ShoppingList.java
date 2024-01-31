package com.example.easyshopper.objects;

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

    // check if product is in the shopping list cart
    public boolean checkForProductInCart (String productName) {
        for(Product i : cart){
            if(i.getProductName().equals(productName))
                return true;
        }
        return false;
    }

    public void addProductToCart(Product product){
        if(!checkForProductInCart(product.getProductName())) {
            cart.add(product);
            // totalAmount += product.getPrice();
            // later
        }
    }
    
    public void removeProductFromCart(Product product){
        if(checkForProductInCart(product.getProductName())) {
            cart.remove(product);
            // totalAmount -= item.getPrice();
            // LATER
        }
    }

    /*
    public void calcTotalAmount(){
        double total = 0;

        for (Product i : carts){
            total += i.getPrice();
        }

        totalAmount = total;
    }*/
}
