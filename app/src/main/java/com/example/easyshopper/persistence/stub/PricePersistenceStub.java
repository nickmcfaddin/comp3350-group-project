package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.persistence.PricePersistence;

import java.util.List;

public class PricePersistenceStub implements PricePersistence {
    public double getPrice(int productID, int storeID) {
        return 0;
    }

    @Override
    public List<Double> getAllPrices(int productID) {
        return null;
    }
}
