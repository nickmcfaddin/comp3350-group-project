package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.ProductPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.easyshopper.persistence.HomeProductPersistence;

public class HomeProductPersistenceStub implements HomeProductPersistence, Serializable{
    private List<HomeProduct> homeProductList;

    //Constructor
    public HomeProductPersistenceStub() {
        this.homeProductList = new ArrayList<>();

        homeProductList.add(new HomeProduct(1, "Apple", 1.00, 0.3, 0.5, 1, 2));
        homeProductList.add(new HomeProduct(2, "Kiwi",  0.5, 11, 1, 0, 1));
        homeProductList.add(new HomeProduct(3, "Banana", 0.3, 27, 1.3, 1, 3));
        homeProductList.add(new HomeProduct(4, "Orange", 0.2, 15, 1, 1, 0));

        homeProductList.add(new HomeProduct(5, "Peanut", 0.2, 15, 1, 0, 0));
        homeProductList.add(new HomeProduct(6, "Pineapple", 0.2, 15, 1, 0, 0));
        homeProductList.add(new HomeProduct(7, "Sausage", 0.2, 15, 1, 0, 0));
    }

    //Get a list of all HomeProduct's
    @Override
    public List<HomeProduct> getExistingHomeProducts() {
        return Collections.unmodifiableList(homeProductList);
    }

    //Obtain a product using its productID
    public HomeProduct getHomeProductById(int productID) {
        for (int i = 0; i < homeProductList.size(); i++){
            if (homeProductList.get(i).getProductID() == productID){
                return homeProductList.get(i);
            }
        }

        return null;
    }
}
