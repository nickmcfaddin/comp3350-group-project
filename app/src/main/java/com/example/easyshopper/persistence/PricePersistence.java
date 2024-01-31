package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Price;

import java.util.List;

public interface PricePersistence {
    double getPrice(int productID, int storeID);

    List<Price> getAllPricesForSameProduct(int productID);
}
