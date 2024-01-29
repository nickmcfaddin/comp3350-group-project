package com.example.easyshopper.persistence;

import java.util.List;

public interface PricePersistence {
    double getPriceByStore(int productID, int storeID);
    List<Double> getAllPrices(int productID);

}
