package com.example.easyshopper.objects;

public class Store {
    private int storeID;
    private String storeName;

    public Store(int storeID, String storeName) {
        this.storeID = storeID;
        this.storeName = storeName;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getStoreName() {
        return storeName;
    }
}
