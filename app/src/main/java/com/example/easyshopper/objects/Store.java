package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Store {
    private final int storeID;
    private final String storeName;

    private ArrayList<Product> storeProducts;

    //Constructor
    public Store(int storeID, String storeName){
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeProducts = new ArrayList<>();
    }

    //Adds a single Product to the Store
    public void addProductToStore(Product product){
        if(product != null){
            storeProducts.add(product);
        }
    }

    // GETTERS
    public String getStoreName(){
        return storeName;
    }
    public int getStoreID() {return storeID;}

    @NonNull
    @Override
    public String toString() {
        return storeName;
    }
}
