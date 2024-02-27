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
    };

    public List<HomeProduct> getStockProduct(){
        return homeProductPersistence.getStockProduct();
    }

    public List<HomeProduct> getSortedStockProduct(){
        return homeProductPersistence.getStockProductSorted();
    }

    public List<HomeProduct> getHiddenProduct(){
        return homeProductPersistence.getHiddenProduct();
    }

    public void incrementStockQuantityBy1(HomeProduct homeProduct, String date){
        homeProductPersistence.incrementStockQuantityBy1(homeProduct, date);
    }

    public void decreaseStockQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.decreaseStockQuantityBy1(homeProduct);
    }

    public void incrementDesiredQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.incrementDesiredQuantityBy1(homeProduct);
    }

    public void decreaseDesiredQuantityBy1(HomeProduct homeProduct){
        homeProductPersistence.decreaseDesiredQuantityBy1(homeProduct);
    }

    public List<String> getHomeProductExpiryDates(HomeProduct homeProduct){
        return homeProductPersistence.getHomeProductExpiryDate(homeProduct);
    }

    public List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct){
        return homeProductPersistence.getHomeProductSortedExpiryDateAscending(homeProduct);
    }

    public List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct) {
        return homeProductPersistence.getHomeProductSortedExpiryDateDescending(homeProduct);
    }
}
