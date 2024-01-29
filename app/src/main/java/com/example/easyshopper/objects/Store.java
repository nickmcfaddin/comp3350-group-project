package com.example.easyshopper.objects;

public class Store {

    String storeName;
    String[] address;

    public Store(String storeName, String[] address){
        this.storeName = storeName;
        this.address = address;
    }

    public String getStoreName(){
        return storeName;
    }

    public String [] getAddress(){
        return address;
    }

}
