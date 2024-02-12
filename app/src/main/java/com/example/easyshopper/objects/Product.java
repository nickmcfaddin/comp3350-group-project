package com.example.easyshopper.objects;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private final int productID;
    private String productName;

    //Nutritional Facts
    private double fat;
    private double carb;
    private double protein;
    private double calories;

    //Constructor
    public Product(int productID, String productName, double fat, double carb, double protein){
        this.productID = productID;
        this.productName = productName;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
        this.calories = fat*9 + carb*4 + protein*4;
    }

    //GETTERS
    public int getProductID() {
        return this.productID;
    };

    public String getProductName() {
        return this.productName;
    }

    public double getFat(){
        return this.fat;
    }

    public double getCarb(){return this.carb;}

    public double getProtein(){
        return this.protein;
    }

    public double getCalories() {return this.calories;}

    @NonNull
    @Override
    public String toString() {
        return productName;
    }
}
