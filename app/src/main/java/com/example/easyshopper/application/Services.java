package com.example.easyshopper.application;

import android.util.Log;

import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.hsqldb.PricePersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.ProductPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.ShoppingListPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.StorePersistenceHSQLDB;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;
import com.example.easyshopper.persistence.stub.ShoppingListPersistenceStub;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;

//Bridge between persistence layer and business layer
public class Services {
    private static ProductPersistence productPersistence = null;
    private static ShoppingListPersistence shoppingListPersistence = null;
    private static PricePersistence pricePersistence = null;
    private static StorePersistence storePersistence = null;

    //GETTERS
    public static ProductPersistence getProductPersistence(boolean forProduction) {
        if(productPersistence == null) {
            if(forProduction) {
                productPersistence = new ProductPersistenceHSQLDB(Main.getDBPathName());
            } else {
                productPersistence = new ProductPersistenceStub();
            }
        }

        return productPersistence;
    }

    public static ShoppingListPersistence getShoppingListPersistence(boolean forProduction) {
        if(shoppingListPersistence == null) {
            shoppingListPersistence = new ShoppingListPersistenceStub();
        }

        return shoppingListPersistence;
    }

    public static PricePersistence getPricePersistence(boolean forProduction) {
        if(pricePersistence == null) {
            if(forProduction) {
                pricePersistence = new PricePersistenceHSQLDB(Main.getDBPathName());
            } else {
                pricePersistence = new PricePersistenceStub();
            }
        }

        return pricePersistence;
    }

    public static StorePersistence getStorePersistence(boolean forProduction) {
        if(storePersistence == null) {
            if(forProduction) {
                storePersistence = new StorePersistenceHSQLDB(Main.getDBPathName());
            } else {
                storePersistence = new StorePersistenceStub();
            }
        }

        return storePersistence;
    }
}
