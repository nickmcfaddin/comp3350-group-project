package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductListPersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListPersistenceStub implements ShoppingListPersistence, Serializable {
    private ProductListPersistence productListPersistenceStub;

    public ShoppingListPersistenceStub(ProductListPersistence productListPersistenceStub) {
        this.productListPersistenceStub = productListPersistenceStub;
    }

    //Returns all currently existing ShoppingList's
    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return productListPersistenceStub.getExistingShoppingLists();
    }

    public boolean listWithStoreExists(Store queryStore) {
        if (queryStore == null) {
            return false;
        }

        for(ShoppingList list : getExistingShoppingLists()) {
            if(list.getStore().getStoreID() == queryStore.getStoreID()) {
                return true;
            }
        }

        return false;
    }
}
