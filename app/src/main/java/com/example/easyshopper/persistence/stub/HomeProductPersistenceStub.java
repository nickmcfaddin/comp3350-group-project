package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.HomeProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.easyshopper.persistence.HomeProductPersistence;

public class HomeProductPersistenceStub implements HomeProductPersistence, Serializable{
    private List<HomeProduct> allProducts;

    //Constructor
    public HomeProductPersistenceStub() {
        this.allProducts = new ArrayList<>();

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

        allProducts.add(apple);
        allProducts.add(kiwi);
        allProducts.add(banana);
        allProducts.add(orange);
        allProducts.add(peanut);
        allProducts.add(pineapple);
        allProducts.add(sausage);
    }

    public List<HomeProduct> getStockProduct(){
        List<HomeProduct> stockProduct = new ArrayList<>();

        for (int i=0; i<allProducts.size(); i++){
            HomeProduct curHomeProduct = allProducts.get(i);

            if (curHomeProduct.getHomeProductDesiredQuantity() != 0 || curHomeProduct.getHomeProductStockQuantity() != 0) {
                stockProduct.add(allProducts.get(i));
            }
        }

        return stockProduct;
    }

    public List<HomeProduct> getStockProductSorted() {
        // sort product by desired/have quantity decreasing order
        // if have = 0 and desired = 1, it will be at the top
        List<HomeProduct> stockProductWithHave0 = new ArrayList<>();
        List<HomeProduct> stockProductWithoutHave0 = new ArrayList<>();

        for (int i=0; i<allProducts.size(); i++){
            HomeProduct curHomeProduct = allProducts.get(i);

            if (curHomeProduct.getHomeProductDesiredQuantity() != 0 || curHomeProduct.getHomeProductStockQuantity() != 0) {
                if (curHomeProduct.getHomeProductStockQuantity() == 0){
                    stockProductWithHave0.add(curHomeProduct);
                }
                else{
                    stockProductWithoutHave0.add(curHomeProduct);
                }
            }
        }

        stockProductWithoutHave0.sort(new Comparator<HomeProduct>() {
            public int compare(HomeProduct product1, HomeProduct product2) {
                // Calculate the ratio of have/desired for each product
                double ratio1 = product1.getHomeProductDesiredQuantity() / (double) product1.getHomeProductStockQuantity();
                double ratio2 = product2.getHomeProductDesiredQuantity() / (double) product2.getHomeProductStockQuantity();

                // Compare the ratios in decreasing order
                return Double.compare(ratio2, ratio1);
            }
        });

        stockProductWithHave0.addAll(stockProductWithoutHave0);

        return stockProductWithHave0;
    }

    public List<HomeProduct> getHiddenProduct(){
        List<HomeProduct> hiddenProduct = new ArrayList<>();

        for (int i=0; i<allProducts.size(); i++){
            if (allProducts.get(i).getHomeProductDesiredQuantity() == 0 && allProducts.get(i).getHomeProductStockQuantity() == 0) {
                hiddenProduct.add(allProducts.get(i));
            }
        }

        return hiddenProduct;
    }

    // METHODS
    public void incrementStockQuantityBy1(HomeProduct homeProduct, String date){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            curHomeProduct.incrementStockQuantityBy1(date);
            allProducts.set(curIndex, curHomeProduct);
        }
    }

    public void incrementDesiredQuantityBy1(HomeProduct homeProduct){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            curHomeProduct.incrementDesiredQuantityBy1();
            allProducts.set(curIndex, curHomeProduct);
        }
    }

    public void decreaseStockQuantityBy1(HomeProduct homeProduct){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            curHomeProduct.decreaseStockQuantityBy1();
            allProducts.set(curIndex, curHomeProduct);
        }
    }

    public void decreaseDesiredQuantityBy1(HomeProduct homeProduct){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            curHomeProduct.decreaseDesiredQuantityBy1();
            allProducts.set(curIndex, curHomeProduct);
        }
    }

    public List<String> getHomeProductExpiryDate(HomeProduct homeProduct){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            return curHomeProduct.getHomeProductExpiryDates();
        }

        return null;
    }

    public List<String> getHomeProductSortedExpiryDateAscending(HomeProduct homeProduct){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            return curHomeProduct.getSortedExpiryDatesAscending();
        }

        return null;
    }

    public List<String> getHomeProductSortedExpiryDateDescending(HomeProduct homeProduct){
        if (allProducts.contains(homeProduct)){
            int curIndex = allProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = allProducts.get(curIndex);

            return curHomeProduct.getSortedExpiryDatesDescending();
        }

        return null;
    }
}
