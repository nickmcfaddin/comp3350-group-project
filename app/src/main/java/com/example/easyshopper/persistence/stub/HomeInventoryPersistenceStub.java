package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.HomeInventory;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeInventoryPersistence;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class HomeInventoryPersistenceStub implements HomeInventoryPersistence, Serializable {
    private HomeInventory homeInventory;

    //Constructor
    public HomeInventoryPersistenceStub() {
        HomeProductPersistence homeProductPersistence = Services.getHomeProductPersistence(false);

        homeInventory = new HomeInventory(homeProductPersistence.getExistingHomeProducts());
    }

    @Override
    public List<HomeProduct> getAllHomeProduct() {
        return homeInventory.getAllProducts();
    }

    public HomeProduct getHomeProductFromHomeInventory(String homeProductName){
        for (HomeProduct homeProduct : homeInventory.getAllProducts()){
            if (Objects.equals(homeProduct.getHomeProductName(), homeProductName)){
                return homeProduct;
            }
        }

        return null;
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
    public void incrementStockQuantityBy1(HomeProduct homeProduct, String date) {
        homeInventory.incrementStockQuantityBy1(homeProduct, date);
    }

    public void decreaseStockQuantityBy1(HomeProduct homeProduct){
        homeInventory.decreaseStockQuantityBy1(homeProduct);
    };

    public void incrementDesiredQuantityBy1(HomeProduct homeProduct){
        homeInventory.incrementDesiredQuantityBy1(homeProduct);
    };

    public void decreaseDesiredQuantityBy1(HomeProduct homeProduct){
        homeInventory.decreaseDesiredQuantityBy1(homeProduct);
    }

    @Override
    public List<String> getHomeProductExpiryDates(HomeProduct homeProduct) {
        return homeInventory.getHomeProductExpiryDate(homeProduct);
    }

    @Override
    public List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct) {
        return homeInventory.getHomeProductSortedExpiryDateAscending(homeProduct);
    }

    @Override
    public List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct) {
        return homeInventory.getHomeProductSortedExpiryDateDescending(homeProduct);
    }
}
