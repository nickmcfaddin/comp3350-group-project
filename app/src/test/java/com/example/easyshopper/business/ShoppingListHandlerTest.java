package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ShoppingListHandlerTest {

    private ShoppingListHandler sLHandlertemp;
    private Product product;
    private Store store;

    @Before
    public void setup(){
        System.out.println("Starting test ShoppingListHandler");
        sLHandlertemp = new ShoppingListHandler();
        product = new Product(10, "TestProduct", 0.1, 0.2, 0.3);
        store = new Store(10, "TestStore");
    }

    @Test
    public void testGetAllShoppingLists() {
        //Tests if we can get all shopping lists (3 = number of lists)
        List<ShoppingList> existLists = sLHandlertemp.getAllShoppingLists();
        assertEquals(3, existLists.size());
    }

    @Test
    public void testCreateAndDeleteShoppingList() {
        //Add a store to the database using the storePersistence
        StorePersistence storePersistence = Services.getStorePersistence();
        storePersistence.addStore(store);

        //Create a list with the new store as the parameter
        sLHandlertemp.createShoppingList(store);

        //Tests that the size of the existing shopping lists reflects the change
        assertEquals(4, sLHandlertemp.getAllShoppingLists().size());

        sLHandlertemp.removeShoppingList(Services.getShoppingListPersistence().getExistingShoppingLists().get(0));
        assertEquals(3, Services.getShoppingListPersistence().getExistingShoppingLists().size());
    }


    @Test
    public void testItemAddAndRemove() {
        sLHandlertemp.addItemToList(product, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(10, sLHandlertemp.getAllShoppingLists().get(0).getItemList().get(4).getProductID());

        sLHandlertemp.removeProduct(product, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(4, sLHandlertemp.getAllShoppingLists().get(0).getItemList().size());
    }


    @Test
    public void testCartTotal(){
        double total = sLHandlertemp.getCartTotal(Services.getShoppingListPersistence().getExistingShoppingLists().get(0));
        assertEquals(13.96, total);
    }

    @Test
    public void testAllShoppingListsCartTotal(){
        double total = sLHandlertemp.getAllShoppingListTotal();
        assertEquals(71.88, total);
    }

}
