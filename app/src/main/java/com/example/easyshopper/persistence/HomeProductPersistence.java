package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.HomeProduct;

import java.util.ArrayList;
import java.util.List;

public interface HomeProductPersistence {
    List<HomeProduct> getStockProduct();

    List<HomeProduct> getStockProductSorted();

    List<HomeProduct> getHiddenProduct();

    void incrementStockQuantityBy1(HomeProduct homeProduct);

    void incrementDesiredQuantityBy1(HomeProduct homeProduct);

    void decreaseStockQuantityBy1(HomeProduct homeProduct);

    void decreaseDesiredQuantityBy1(HomeProduct homeProduct);

    List<String> getHomeProductExpiryDate(HomeProduct homeProduct);

    List<String> getHomeProductSortedExpiryDateAscending(HomeProduct homeProduct);

    List<String> getHomeProductSortedExpiryDateDescending(HomeProduct homeProduct);

    List<HomeProduct> getHomeProducts();

}
