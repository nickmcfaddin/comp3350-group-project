package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

import java.io.Serializable;
import java.util.List;

public class StoreHandler implements Serializable {
    private static StorePersistence storePersistence;

    public StoreHandler(boolean forProduction) {
        storePersistence = Services.getStorePersistence(forProduction);
    }

    public static Store getStoreById(int storeID) {
        return storePersistence.getStoreById(storeID);
    }

    public static List<Store> getExistingStores() {
        return storePersistence.getExistingStores();
    }
}
