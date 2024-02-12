package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.StorePersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorePersistenceStub implements StorePersistence, Serializable {
    private List<Store> storeList;

    //Constructor
    public StorePersistenceStub(){
        storeList = new ArrayList<>();

        Store costco = new Store(1, "Costco");
        Store walmart = new Store(2, "Walmart");
        Store superstore = new Store(3, "SuperStore");

        storeList.add(costco);
        storeList.add(walmart);
        storeList.add(superstore);

        addAllProductsToStore(costco);
        addAllProductsToStore(walmart);
        addAllProductsToStore(superstore);
    }

    //Returns a list of all existing Store's
    public List<Store> getExistingStores(){return Collections.unmodifiableList(storeList);};

    //Adds all Product's to the identified Store
    public void addAllProductsToStore(Store store){
        Services services = new Services();
        ProductPersistence productPersistence = services.getProductPersistence();

        List<Product> existingProducts = productPersistence.getExistingProducts();
        for (Product product : existingProducts){
            store.addProductToStore(product);
        }
    }

    //Returns a single Store identified by a storeID
    public Store getStoreById(int storeID){
        for (int i=0; i<storeList.size(); i++){
            if (storeList.get(i).getStoreID() == storeID)
            {
                return storeList.get(i);
            }
        }
        return null;
    }

    public void addStore(Store store){
        if(store != null){
            storeList.add(store);
        }
    }
}
