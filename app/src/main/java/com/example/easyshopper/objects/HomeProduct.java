package com.example.easyshopper.objects;

public class HomeProduct extends Product {
    private int haveQuantity;
    private int desiredQuantity;

    public HomeProduct(int productID, String productName, double fat, double carb, double protein, int haveQuantity, int desiredQuantity) {
        super(productID, productName, fat, carb, protein);
        this.haveQuantity = haveQuantity;
        this.desiredQuantity = desiredQuantity;
    }

    // GETTERS
    public int getHaveQuantity() {
        return haveQuantity;
    }

    public int getDesiredQuantity() {
        return desiredQuantity;
    }

    // METHODS
    public void incrementHaveQuantity(){
        haveQuantity++;
    }

    public void reduceHaveQuantity(){
        if (haveQuantity > 0){
            haveQuantity--;
        }
    }

    public void incrementDesiredQuantity(){
        desiredQuantity++;
    }

    public void reduceDesiredQuantity(){
        if (desiredQuantity > 0){
            desiredQuantity--;
        }
    }
}