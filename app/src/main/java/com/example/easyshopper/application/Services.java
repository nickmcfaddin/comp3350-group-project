package com.example.easyshopper.application;

import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.UserPersistence;

//Bridge between persistence layer and business layer
public class Services {

    //Declarations of persistence instances
    private static ProductPersistence productPersistence = null;
    private static UserPersistence userPersistence = null;
    private static ShoppingListPersistence shoppingListPersistence = null;
    private static PricePersistence pricePersistence = null;
    private static StorePersistence storePersistence = null;

    //GETTERS
    public static ProductPersistence getProductPersistence() {
        return productPersistence;
    }

    public static UserPersistence getUserPersistence() {
        return userPersistence;
    }

    public static ShoppingListPersistence getShoppingListPersistence() {return shoppingListPersistence;}
    public static PricePersistence getPricePersistence() {return pricePersistence;}
    public static StorePersistence getStorePersistence() {return storePersistence;}

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
