package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.HomeProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.easyshopper.persistence.HomeProductPersistence;

public class HomeProductPersistenceStub implements HomeProductPersistence, Serializable{
    private ArrayList<HomeProduct> homeProductList;

    //Constructor
    public HomeProductPersistenceStub() {
        this.homeProductList = new ArrayList<>();

        List<String> appleExpiryDate = new ArrayList<>();
        List<String> kiwiExpiryDate = new ArrayList<>();
        List<String> bananaExpiryDate = new ArrayList<>();
        List<String> peanutExpiryDate = new ArrayList<>();
        List<String> orangeExpiryDate = new ArrayList<>();
        List<String> pineappleExpiryDate = new ArrayList<>();
        List<String> sausageExpiryDate = new ArrayList<>();
        appleExpiryDate.add("2023-11-15");
        appleExpiryDate.add("2024-10-20");
        appleExpiryDate.add("2023-12-31");
        kiwiExpiryDate.add("2023-12-31");
        kiwiExpiryDate.add("2024-01-15");
        bananaExpiryDate.add("2024-01-15");
        orangeExpiryDate.add("2024-03-03");

        HomeProduct apple = new HomeProduct(1, "Apple", 1.00, 0.3, 0.5, 3, 2, appleExpiryDate);
        HomeProduct kiwi = new HomeProduct(2, "Kiwi",  0.5, 11, 1, 2, 1, kiwiExpiryDate);
        HomeProduct banana = new HomeProduct(3, "Banana", 0.3, 27, 1.3, 1, 3, bananaExpiryDate);
        HomeProduct orange = new HomeProduct(4, "Orange", 0.2, 15, 1, 1, 0, orangeExpiryDate);
        HomeProduct peanut = new HomeProduct(5, "Peanut", 0.2, 15, 1, 0, 0, peanutExpiryDate);
        HomeProduct pineapple = new HomeProduct(6, "Pineapple", 0.2, 15, 1, 0, 0, pineappleExpiryDate);
        HomeProduct sausage = new HomeProduct(7, "Sausage", 0.2, 15, 1, 0, 0, sausageExpiryDate);

        homeProductList.add(apple);
        homeProductList.add(kiwi);
        homeProductList.add(banana);
        homeProductList.add(orange);
        homeProductList.add(peanut);
        homeProductList.add(pineapple);
        homeProductList.add(sausage);
    }

    //Get a list of all HomeProduct's
    @Override
    public ArrayList<HomeProduct> getExistingHomeProducts() {
        return homeProductList;
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
