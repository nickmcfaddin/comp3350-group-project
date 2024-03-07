package com.example.easyshopper.persistence.hsqldb;

import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductListPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.io.Serializable;
import java.util.List;

public class ShoppingListPersistenceHSQLDB implements ShoppingListPersistence, Serializable {
    private ProductListPersistence productListPersistenceHSQLDB;

    public ShoppingListPersistenceHSQLDB(ProductListPersistence productListPersistenceHSQLDB) {
        this.productListPersistenceHSQLDB = productListPersistenceHSQLDB;
    }

    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return productListPersistenceHSQLDB.getExistingShoppingLists();
    }

    @Override
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
