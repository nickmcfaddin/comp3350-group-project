package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.HomeProduct;

import java.util.List;

public interface HomeProductPersistence {
    List<HomeProduct> getStockProduct();

    List<HomeProduct> getStockProductSorted();

    List<HomeProduct> getHiddenProduct();

    void incrementStockQuantity(HomeProduct homeProduct);

    void incrementDesiredQuantity(HomeProduct homeProduct);

    void decreaseStockQuantity(HomeProduct homeProduct);

    void decreaseDesiredQuantity(HomeProduct homeProduct);

    List<String> getHomeProductExpiryDate(HomeProduct homeProduct);

    List<String> getHomeProductSortedExpiryDateAscending(HomeProduct homeProduct);

    List<String> getHomeProductSortedExpiryDateDescending(HomeProduct homeProduct);

    List<HomeProduct> getHomeProducts();

}
