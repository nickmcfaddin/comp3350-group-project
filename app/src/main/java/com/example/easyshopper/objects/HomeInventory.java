package com.example.easyshopper.objects;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeInventory implements Serializable{
    List<HomeProduct> allProducts;

    // Using singleton for the object
    private HomeInventory(){
        allProducts = new ArrayList<>();
    }

    public HomeInventory(List<HomeProduct> allProducts){
        this.allProducts = allProducts;
    }

    // GETTERS
    public List<HomeProduct> getAllProducts(){
        return allProducts;
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
