package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public interface HomeInventoryPersistence {
    // get all products
    List<HomeProduct> getAllHomeProduct();

    // get home product
    HomeProduct getHomeProductFromHomeInventory(String homeProductName);

    // get stock home products
    List<HomeProduct> getStockProduct();

    // get sorted stock home products
    List<HomeProduct> getSortedStockProduct();

    // get hidden home products
    List<HomeProduct> getHiddenProduct();

    // other methods
    void incrementStockQuantityBy1(HomeProduct homeProduct, String date);

    void decreaseStockQuantityBy1(HomeProduct homeProduct);

    void incrementDesiredQuantityBy1(HomeProduct homeProduct);

    void decreaseDesiredQuantityBy1(HomeProduct homeProduct);

    List<String> getHomeProductExpiryDates(HomeProduct homeProduct);

    List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct);

    List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct);
}
