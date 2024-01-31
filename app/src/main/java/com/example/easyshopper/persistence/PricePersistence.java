package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Price;

import java.util.List;

public interface PricePersistence {
    double getPrice(int productID, int storeID);

    List<Price> getPriceByStore(int storeID);

    List<Price> getPriceByProduct(int productID);
    List<Price> getAllPrices(int productID);

    void addPrice(int productID, int StoreID);
}
