package com.example.easyshopper.objects;

import java.util.ArrayList;

public class Store {
    // KEVIN
    private final int storeID;
    private final String storeName;

    private ArrayList<Product> storeProducts;

    private int productID;

    public Store(int storeID, String storeName){
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeProducts = new ArrayList<>();
    }

    // GETTER FUNCTIONS
    public String getStoreName(){
        return storeName;
    }

    public int getStoreID() {
        return storeID;
    }
}
