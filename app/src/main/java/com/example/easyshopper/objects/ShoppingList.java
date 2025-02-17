package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.util.List;

//List is created per store, products added to list
public class ShoppingList extends ProductList {
    private Store store;

    //Constructor
    public ShoppingList(Store store){
        super();
        this.store = store;
    }

    //Not included in unit testing as this is pulling from the db
    public ShoppingList(String listID, List<Product> cart, Store store){
        super(listID, cart);
        this.store = store;
    }

    // GETTERS
    public String getListName() {
        return store.getStoreName();
    }

    public Store getStore(){return store;}

    @NonNull
    @Override
    public String toString() {
        return store.getStoreName();
    }

    public void add(ProductListVisitor visitor) {
        visitor.add(this);
    }

    public void delete(ProductListVisitor visitor){
        visitor.delete(this);
    }

}
