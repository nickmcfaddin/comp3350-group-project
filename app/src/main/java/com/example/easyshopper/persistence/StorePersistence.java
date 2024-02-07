package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;

import java.util.List;

//Implementations are all currently found in StorePersistenceStub
public interface StorePersistence {

    //Returns a list of all existing Store's
    List<Store> getExistingStores();

    //Returns a list of every Product in a selected Store
    List<Product> getAllProductInStore(Store store);

    //Returns a single Store identified by a storeID
    Store getStoreById(int storeID);

    //Returns a single Store identified by a storeName
    Store getStoreByName(String storeName);

    void removeStore(Store store);

    void addStore(Store store);
}
