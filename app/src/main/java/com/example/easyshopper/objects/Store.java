package com.example.easyshopper.objects;

public class Store {

    private String storeName;
    private String[] address;

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
