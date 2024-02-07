package com.example.easyshopper.logic.exceptions;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

import java.util.List;

public class ShoppingListHandlerTest {

    public void setup(){
        System.out.println("Starting test ShoppingListHandler");
    }

    public void test(){
        ShoppingListHandler sLHandlertemp = new ShoppingListHandler();
        ProductHandler pHandlerTemp = new ProductHandler();

        List<ShoppingList> existLists = sLHandlertemp.getAllShoppingLists();
        System.out.println("Testing getAllShoppingLists");
        assert(existLists != null);
        System.out.println("Pass");

        //add a test store to store list
        Product productTemp = pHandlerTemp.getProductByID(1);
        Store store = new Store(10, "testStore");
        ShoppingList testList = new ShoppingList("TestList", store);

        StorePersistence storePersistence = Services.getStorePersistence();
        storePersistence.addStore(store);

        System.out.println("Testing createShoppingList");
        sLHandlertemp.createShoppingList(testList);
        assert(existLists.size() != Services.getShoppingListPersistence().getExistingShoppingLists().size());
        System.out.println("Pass");


        System.out.println("Testing addItemToList");
        sLHandlertemp.addItemToList(productTemp, testList);
        assert(!testList.isEmpty());
        System.out.println("Pass");

        System.out.println("Testing removeProduct");
        sLHandlertemp.removeProduct(productTemp, testList);
        assert (testList.isEmpty());
        System.out.println("Pass");

        System.out.println("Testing removeShoppingList");
        sLHandlertemp.removeShoppingList(testList);
        assert(Services.getShoppingListPersistence().getExistingShoppingLists().size() == existLists.size());
        System.out.println("Pass");

        storePersistence.removeStore(store);
    }
}
