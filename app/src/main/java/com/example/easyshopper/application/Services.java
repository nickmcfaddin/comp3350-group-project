package com.example.easyshopper.application;

import com.example.easyshopper.persistence.HomeProductPersistence;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductListPersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.RequestListPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.UserPersistence;
import com.example.easyshopper.persistence.hsqldb.HomeProductPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.PricePersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.ProductListPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.ProductPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.RequestListPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.ShoppingListPersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.StorePersistenceHSQLDB;
import com.example.easyshopper.persistence.hsqldb.UserPersistenceHSQLDB;
import com.example.easyshopper.persistence.stub.HomeProductPersistenceStub;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;
import com.example.easyshopper.persistence.stub.ProductListPersistenceStub;
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;
import com.example.easyshopper.persistence.stub.RequestListPersistenceStub;
import com.example.easyshopper.persistence.stub.ShoppingListPersistenceStub;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;
import com.example.easyshopper.persistence.stub.UserPersistenceStub;

//Bridge between persistence layer and business layer
public class Services {
    private static ProductPersistence productPersistence = null;
    private static ShoppingListPersistence shoppingListPersistence = null;
    private static PricePersistence pricePersistence = null;
    private static StorePersistence storePersistence = null;
    private static HomeProductPersistence homeProductPersistence = null;
    private static UserPersistence userPersistence = null;
    private static RequestListPersistence requestListPersistence = null;
    private static ProductListPersistence productListPersistence = null;

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
            if(forProduction) {
                shoppingListPersistence = new ShoppingListPersistenceHSQLDB(getProductListPersistence(forProduction));
            } else {
                shoppingListPersistence = new ShoppingListPersistenceStub(getProductListPersistence(forProduction));
            }
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

    public static HomeProductPersistence getHomeProductPersistence(boolean forProduction) {
        if(homeProductPersistence == null) {
            if(forProduction) {
                homeProductPersistence = new HomeProductPersistenceHSQLDB(Main.getDBPathName());
            } else {
                homeProductPersistence = new HomeProductPersistenceStub();
            }
        }

        return homeProductPersistence;
    }

    public static RequestListPersistence getRequestListPersistence(boolean forProduction) {
        if(requestListPersistence == null) {
            if(forProduction) {
                requestListPersistence = new RequestListPersistenceHSQLDB(getProductListPersistence(forProduction));
            } else {
                requestListPersistence = new RequestListPersistenceStub(getProductListPersistence(forProduction));
            }
        }
        return requestListPersistence;
    }

    public static UserPersistence getUserPersistence(boolean forProduction){
        if(userPersistence == null) {
            if(forProduction) {
                userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
            } else {
                userPersistence = new UserPersistenceStub();
            }
        }

        return userPersistence;
    }
    public static ProductListPersistence getProductListPersistence(boolean forProduction){
        if(productListPersistence == null) {
            if(forProduction) {
                productListPersistence = new ProductListPersistenceHSQLDB(Main.getDBPathName());
            } else {
                productListPersistence = new ProductListPersistenceStub();
            }
        }

        return productListPersistence;
    }
}
