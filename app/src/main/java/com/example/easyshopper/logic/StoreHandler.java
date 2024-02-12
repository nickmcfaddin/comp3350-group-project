package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

import java.io.Serializable;
import java.util.List;

public class StoreHandler implements Serializable {
    StorePersistence storePersistence = Services.getStorePersistence();

    public Store getStoreById(int storeID) {
        return storePersistence.getStoreById(storeID);
    }

    public List<Store> getExistingStores() {
        return storePersistence.getExistingStores();
     }
}
