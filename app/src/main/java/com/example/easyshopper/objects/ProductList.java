package com.example.easyshopper.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ProductList implements Serializable {
    private String listID;
    private List<Product> cart;

    public ProductList() {
        this.listID = UUID.randomUUID().toString();
        this.cart = new ArrayList<>();
    }

    //Not included in testing, constructor for db ease
    public ProductList(String listID, List<Product> cart) {
        this.listID = listID;
        this.cart = cart;
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
    public void removeProductFromCart(Product product) {
        if (checkForProductInCart(product)) {
            cart.remove(product);
        }
    }
    public String getListID() {
        return listID;
    }

    public List<Product> getCart() {
        return cart;
    }
}
