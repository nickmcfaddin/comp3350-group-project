package com.example.easyshopper.objects;

public class Store {
    // KEVIN
    private final int storeID;
    private final String storeName;

    public Store(int storeID, String storeName){
        this.storeID = storeID;
        this.storeName = storeName;
    }

    // GETTER FUNCTIONS
    public String getStoreName(){
        return storeName;
    }

    public int getStoreID() {
        return storeID;
    }
}
