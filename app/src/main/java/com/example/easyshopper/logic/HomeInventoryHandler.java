package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.io.Serializable;
import java.util.List;

public class  HomeInventoryHandler implements Serializable {
    private static HomeProductPersistence homeProductPersistence;

    //constructor
    public HomeInventoryHandler(boolean forProduction){
        homeProductPersistence = Services.getHomeProductPersistence(forProduction);
    }

    public static List<HomeProduct> getStockProduct(){
        return homeProductPersistence.getStockProduct();
    }

    public static List<HomeProduct> getSortedStockProduct(){
        return homeProductPersistence.getStockProductSorted();
    }

    public static List<HomeProduct> getHiddenProduct(){
        return homeProductPersistence.getHiddenProduct();
    }

    public static void incrementStockQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.incrementStockQuantityBy1(homeProduct);
    }

    public static void decreaseStockQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.decreaseStockQuantityBy1(homeProduct);
    }

    public static void incrementDesiredQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.incrementDesiredQuantityBy1(homeProduct);
    }

    public static void decreaseDesiredQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.decreaseDesiredQuantityBy1(homeProduct);
    }

    public static List<String> getHomeProductExpiryDates(HomeProduct homeProduct){
        return homeProductPersistence.getHomeProductExpiryDate(homeProduct);
    }

    public static List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct){
        return homeProductPersistence.getHomeProductSortedExpiryDateAscending(homeProduct);
    }

    public static List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct) {
        return homeProductPersistence.getHomeProductSortedExpiryDateDescending(homeProduct);
    }

    public static List<HomeProduct> getHomeProducts() {
        return homeProductPersistence.getHomeProducts();
    }
}
