package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.HomeInventory;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeInventoryPersistence;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.io.Serializable;
import java.util.List;

public class HomeInventoryPersistenceStub implements HomeInventoryPersistence, Serializable {
    private HomeInventory homeInventory;

    //Constructor
    public HomeInventoryPersistenceStub() {
        //Setup connections to HomeProductPersistence stubs
        Services services = new Services();
        HomeProductPersistence homeProductPersistence = services.getHomeProductPersistence();

        homeInventory = new HomeInventory(homeProductPersistence.getExistingHomeProducts());
    }

    @Override
    public List<HomeProduct> getAllHomeProduct() {
        return homeInventory.getAllProducts();
    }

    @Override
    public List<HomeProduct> getStockProduct() {
        return homeInventory.getStockProduct();
    }

    @Override
    public List<HomeProduct> getSortedStockProduct() {
        return homeInventory.getStockProductSorted();
    }

    @Override
    public List<HomeProduct> getHiddenProduct() {
        return homeInventory.getHiddenProduct();
    }

    @Override
    public void incrementStockQuantityBy1(HomeProduct homeProduct) {
        homeInventory.incrementStockQuantityBy1(homeProduct);
    }

    public void decreaseStockQuantityBy1(HomeProduct homeProduct){
        homeInventory.decreaseStockQuantityBy1(homeProduct);
    };

    public void incrementDesiredQuantityBy1(HomeProduct homeProduct){
        homeInventory.incrementDesiredQuantityBy1(homeProduct);
    };

    public void decreaseDesiredQuantityBy1(HomeProduct homeProduct){
        homeInventory.decreaseDesiredQuantityBy1(homeProduct);
    };
}
