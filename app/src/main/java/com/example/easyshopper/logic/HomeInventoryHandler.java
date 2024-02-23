package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeInventoryPersistence;

import java.io.Serializable;
import java.util.List;

public class HomeInventoryHandler implements Serializable {
    private HomeInventoryPersistence homeInventoryPersistence = Services.getHomeInventoryPersistence();

    //constructor
    public HomeInventoryHandler(){};

    public List<HomeProduct> getAllHomeProduct(){
        return homeInventoryPersistence.getAllHomeProduct();
    }

    public List<HomeProduct> getStockProduct(){
        return homeInventoryPersistence.getStockProduct();
    }

    public List<HomeProduct> getSortedStockProduct(){
        return homeInventoryPersistence.getSortedStockProduct();
    }

    public List<HomeProduct> getHiddenProduct(){
        return homeInventoryPersistence.getHiddenProduct();
    }

    public void incrementStockQuantityBy1(HomeProduct homeProduct){
        homeInventoryPersistence.incrementStockQuantityBy1(homeProduct);
    }

    public void decreaseStockQuantityBy1(HomeProduct homeProduct){
        homeInventoryPersistence.decreaseStockQuantityBy1(homeProduct);
    }

    public void incrementDesiredQuantityBy1(HomeProduct homeProduct){
        homeInventoryPersistence.incrementDesiredQuantityBy1(homeProduct);
    }

    public void decreaseDesiredQuantityBy1(HomeProduct homeProduct){
        homeInventoryPersistence.decreaseDesiredQuantityBy1(homeProduct);
    }
}
