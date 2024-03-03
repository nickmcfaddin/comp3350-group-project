package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {
    private final int storeID;
    private final String storeName;

    //Constructor
    public Store(int storeID, String storeName){
        this.storeID = storeID;
        this.storeName = storeName;
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
