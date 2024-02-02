package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StorePersistenceStub implements StorePersistence {
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
    }

    //Returns a list of all existing Store's
    public List<Store> getExistingStores(){return Collections.unmodifiableList(storeList);};

    //Returns a list of every Product in a selected Store
    public List<Product> getAllProductInStore(Store store){
        for (int i=0; i<storeList.size(); i++){
            if (Objects.equals(storeList.get(i).getStoreName(), store.getStoreName())){
                return storeList.get(i).getStoreProducts();
            }
        }
        return null;
    };

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

    //Returns a single Store identified by a storeName
    public Store getStoreByName(String storeName){
        for (int i=0; i<storeList.size(); i++){
            if (Objects.equals(storeList.get(i).getStoreName(), storeName)){
                return storeList.get(i);
            }
        }
        return null;
    }
}
