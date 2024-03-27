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

    public static void incrementStockQuantity(HomeProduct homeProduct){
        if(validHomeProduct(homeProduct)) {
            homeProductPersistence.incrementStockQuantity(homeProduct);
        }
    }

    public static void decreaseStockQuantity(HomeProduct homeProduct){
        if(validHomeProduct(homeProduct)) {
            homeProductPersistence.decreaseStockQuantity(homeProduct);
        }
    }

    public static void incrementDesiredQuantity(HomeProduct homeProduct){
        if(validHomeProduct(homeProduct)) {
            homeProductPersistence.incrementDesiredQuantity(homeProduct);
        }
    }

    public static void decreaseDesiredQuantity(HomeProduct homeProduct){
        if(validHomeProduct(homeProduct)) {
            homeProductPersistence.decreaseDesiredQuantity(homeProduct);
        }
    }

    public static List<String> getHomeProductExpiryDates(HomeProduct homeProduct){
        if(validHomeProduct(homeProduct)) {
            return homeProductPersistence.getHomeProductExpiryDate(homeProduct);
        }

        return null;
    }

    public static List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct){
        if(validHomeProduct(homeProduct)) {
            return homeProductPersistence.getHomeProductSortedExpiryDateAscending(homeProduct);
        }

        return null;
    }

    public static List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct) {
        if(validHomeProduct(homeProduct)) {
            return homeProductPersistence.getHomeProductSortedExpiryDateDescending(homeProduct);
        }

        return null;
    }

    public static List<HomeProduct> getHomeProducts() {
        return homeProductPersistence.getHomeProducts();
    }

    private static boolean validHomeProduct(HomeProduct homeProduct) {
        if(homeProduct == null) {
            return false;
        }

        if(!getHomeProducts().contains(homeProduct)) {
            return false;
        }

        return true;
    }
}
