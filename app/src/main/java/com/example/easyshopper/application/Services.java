package com.example.easyshopper.application;

import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.UserListPersistence;
import com.example.easyshopper.persistence.UserPersistence;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;
import com.example.easyshopper.persistence.stub.ShoppingListPersistenceStub;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;
import com.example.easyshopper.persistence.stub.UserListPersistenceStub;
import com.example.easyshopper.persistence.stub.UserPersistenceStub;

//Bridge between persistence layer and business layer
public class Services {
    //GETTERS
    public static ProductPersistence getProductPersistence() {return new ProductPersistenceStub();}
    public static ShoppingListPersistence getShoppingListPersistence() {return new ShoppingListPersistenceStub();}
    public static PricePersistence getPricePersistence() {return new PricePersistenceStub();}
    public static StorePersistence getStorePersistence() {return new StorePersistenceStub();}

    public static UserListPersistence getUserListPersistence(){ return new UserListPersistenceStub();}


    public static UserPersistence getUserPersistence() {return new UserPersistenceStub();}
}
