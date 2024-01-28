package com.example.easyshopper.application;

import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.UserPersistence;

public class Services {
    private static ProductPersistence productPersistence = null;
    private static UserPersistence userPersistence = null;
    private static ShoppingListPersistence shoppingListPersistence = null;

    public static ProductPersistence getProductPersistence() {
        return productPersistence;
    }

    public static UserPersistence getUserPersistence() {
        return userPersistence;
    }

    public static ShoppingListPersistence getShoppingListPersistence() {
        return shoppingListPersistence;
    }

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
