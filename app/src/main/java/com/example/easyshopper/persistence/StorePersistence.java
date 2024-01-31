package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;

import java.util.List;

public interface StorePersistence {
    List<Store> getExistingStores();

    List<Product> getAllProductInStore(Store store);

    Store getStoreById(int storeID);

    Store getStoreByName(String storeName);
}
