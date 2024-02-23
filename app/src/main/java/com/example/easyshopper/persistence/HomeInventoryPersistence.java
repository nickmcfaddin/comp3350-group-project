package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public interface HomeInventoryPersistence {
    // get all products
    List<HomeProduct> getAllHomeProduct();

    // get stock home products
    List<HomeProduct> getStockProduct();

    // get sorted stock home products
    List<HomeProduct> getSortedStockProduct();

    // get hidden home products
    List<HomeProduct> getHiddenProduct();

    // other methods
    void incrementStockQuantityBy1(HomeProduct homeProduct);

    void decreaseStockQuantityBy1(HomeProduct homeProduct);

    void incrementDesiredQuantityBy1(HomeProduct homeProduct);

    void decreaseDesiredQuantityBy1(HomeProduct homeProduct);
}
