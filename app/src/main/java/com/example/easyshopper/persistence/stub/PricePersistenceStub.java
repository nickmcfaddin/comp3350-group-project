package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.persistence.PricePersistence;

import java.util.List;

public class PricePersistenceStub implements PricePersistence {
    public double getPrice(int productID, int storeID) {
        return 0;
    }

    @Override
    public List<Price> getPriceByStore(int storeID) {
        return null;
    }

    @Override
    public List<Price> getPriceByProduct(int productID) {
        return null;
    }

    @Override
    public List<Price> getAllPrices(int productID) {
        return null;
    }

    @Override
    public void addPrice(int productID, int priceID) {

    }
}
