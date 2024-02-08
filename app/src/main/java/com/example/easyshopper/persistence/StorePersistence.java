package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Store;

import java.util.List;

//Implementations are all currently found in StorePersistenceStub
public interface StorePersistence {

    //Returns a list of all existing Store's
    List<Store> getExistingStores();

    //Returns a single Store identified by a storeID
    Store getStoreById(int storeID);

    void addStore(Store store);
}
