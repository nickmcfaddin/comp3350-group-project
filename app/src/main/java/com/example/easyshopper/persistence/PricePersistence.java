package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Price;

import java.util.List;

//Implementations are all currently found in PricePersistenceStub
public interface PricePersistence {

    //Returns a Price of a Product, identified by the productID and storeID
    double getPrice(int productID, int storeID);

    //Returns all Price's for the same Product across all Store's identified by the productID
    List<Price> getAllPricesForSameProduct(int productID);
}
