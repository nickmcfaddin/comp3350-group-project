package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;

import java.util.List;

//Implementations are all currently found in PricePersistenceStub
public interface PricePersistence {

    //Returns a Price of a Product, identified by the productID and storeID
    double getPrice(Product product, Store store);

    //Returns all Price's for the same Product across all Store's identified by the productID
    List<Price> getAllPricesForSameProduct(Product product);
}
