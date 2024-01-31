package com.example.easyshopper.objects;

public class Price {
    private final int storeID;
    private final int productID;
    private final float price;

    public Price(int storeID, int productID, float price){
        this.storeID = storeID;
        this.productID = productID;
        this.price = price;
    }

    // GETTER
    public int getStoreID(){
        return  storeID;
    }

    public int getProductID(){
        return productID;
    }

    public float getPrice(){
        return price;
    }
}
