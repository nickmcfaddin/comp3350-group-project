package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

public class StoreHandler {
    StorePersistence storePersistence = Services.getStorePersistence();
    public Store getStoreById(int storeID)
    {
        return storePersistence.getStoreById(storeID);
    }

}
