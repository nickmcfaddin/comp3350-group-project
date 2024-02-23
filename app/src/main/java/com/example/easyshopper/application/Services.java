package com.example.easyshopper.application;

import com.example.easyshopper.persistence.HomeInventoryPersistence;
import com.example.easyshopper.persistence.HomeProductPersistence;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.stub.HomeInventoryPersistenceStub;
import com.example.easyshopper.persistence.stub.HomeProductPersistenceStub;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;
import com.example.easyshopper.persistence.stub.ShoppingListPersistenceStub;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;

//Bridge between persistence layer and business layer
public class Services {
    //GETTERS
    public static ProductPersistence getProductPersistence() {return new ProductPersistenceStub();}
    public static ShoppingListPersistence getShoppingListPersistence() {return new ShoppingListPersistenceStub();}
    public static PricePersistence getPricePersistence() {return new PricePersistenceStub();}
    public static StorePersistence getStorePersistence() {return new StorePersistenceStub();}
    public static HomeProductPersistence getHomeProductPersistence() {return new HomeProductPersistenceStub();}
    public static HomeInventoryPersistence getHomeInventoryPersistence() {return new HomeInventoryPersistenceStub();}
}
