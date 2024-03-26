package com.example.easyshopper.objects;

import java.io.Serializable;

//Price class acts as link between Store's and Product's.
public class Price implements Serializable, Comparable<Price>  {
    private final int storeID;
    private final int productID;
    private final double price;

    //Constructor
    public Price(int storeID, int productID, double price){
        this.storeID = storeID;
        this.productID = productID;
        this.price = price;
    }

    // GETTERS
    public int getStoreID(){
        return  storeID;
    }
    public int getProductID(){
        return productID;
    }

    public double getPrice(){
        return price;
    }

    public String getPriceFormatted() {
        return "$" + String.format("%.2f", price);
    }

    @Override
    public int compareTo(Price other){
        return Double.compare(this.price, other.price);
    }

}
