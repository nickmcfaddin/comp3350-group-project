package com.example.easyshopper.application;

import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.UserPersistence;
import com.example.easyshopper.persistence.stub.PricePersistenceStub;
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;
import com.example.easyshopper.persistence.stub.ShoppingListPersistenceStub;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;
import com.example.easyshopper.persistence.stub.UserPersistenceStub;

//Bridge between persistence layer and business layer
public class Services {

    //Declarations of persistence instances
    private static ProductPersistence productPersistence = null;
    private static UserPersistence userPersistence = null;
    private static ShoppingListPersistence shoppingListPersistence = null;
    private static PricePersistence pricePersistence = null;
    private static StorePersistence storePersistence = null;

    //GETTERS
    public static ProductPersistence getProductPersistence() {return new ProductPersistenceStub();}

    public static UserPersistence getUserPersistence() {
        return new UserPersistenceStub();
    }

    public static ShoppingListPersistence getShoppingListPersistence() {return new ShoppingListPersistenceStub();}
    public static PricePersistence getPricePersistence() {return new PricePersistenceStub();}
    public static StorePersistence getStorePersistence() {return new StorePersistenceStub();}

    /**
     * clean
     *
     * Reset all services so to be reloaded from scratch next time they are referenced
     */
    public static void clean() {
        productPersistence = null;
        userPersistence = null;
        shoppingListPersistence = null;
    }
}
