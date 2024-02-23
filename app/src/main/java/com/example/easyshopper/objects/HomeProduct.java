package com.example.easyshopper.objects;

public class HomeProduct extends Product {
    int stockQuantity;
    int desiredQuantity;

    public HomeProduct(int productID, String productName, double fat, double carb, double protein, int stockQuantity, int desiredQuantity) {
        super(productID, productName, fat, carb, protein);
        this.stockQuantity = stockQuantity;
        this.desiredQuantity = desiredQuantity;
    }

    // GETTERS
    public int getHomeProductStockQuantity(){
        return stockQuantity;
    }

    public int getHomeProductDesiredQuantity(){
        return desiredQuantity;
    }

    // increase and decrease stock and desired quantity
    public void incrementStockQuantityBy1(){
        stockQuantity++;
    }

    public void incrementDesiredQuantityBy1(){
        desiredQuantity++;
    }

    public void decreaseStockQuantityBy1(){
        if (stockQuantity > 0){
            stockQuantity--;
        }
    }

    public void decreaseDesiredQuantityBy1(){
        if (desiredQuantity > 0){
            desiredQuantity--;
        }
    }
}