package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.io.File;
import java.io.IOException;

public class ShoppingListHandlerIT {

    private ShoppingListHandler sLHandlertemp;
    private Product product;
    private Store store;
    private File tempDB;
    boolean forProduction;

    @Before
    public void setup() throws IOException{
        System.out.println("Starting test ShoppingListHandler");

        this.tempDB = TestUtils.copyDB();
        forProduction = true;

        sLHandlertemp = new ShoppingListHandler(forProduction);

        //Product and store to use in tests
        product = new Product(1, "Apple", 0.26, 13.81, 0.17, 14);
        store = new Store(1, "Loblaws");
    }

    @Test
    public void testGetAllShoppingLists() {
        //Tests if we initially have our zero lists, will be modified in next test
        List<ShoppingList> existLists = sLHandlertemp.getAllShoppingLists();
        assertEquals(0, existLists.size());
    }

    @Test
    public void testCreateAndDeleteShoppingList() {//Create a list with the new store as the parameter
        sLHandlertemp.createShoppingList(store);

        //Tests that the size of the existing shopping lists reflects the change
        assertEquals(1, sLHandlertemp.getAllShoppingLists().size());

        sLHandlertemp.removeShoppingList(Services.getShoppingListPersistence(forProduction).getExistingShoppingLists().get(0));
        assertEquals(0, Services.getShoppingListPersistence(forProduction).getExistingShoppingLists().size());

        sLHandlertemp.createShoppingList(null);
        assertEquals(0, Services.getShoppingListPersistence(forProduction).getExistingShoppingLists().size());
    }

    @Test
    public void testItemAddAndRemove() {
        sLHandlertemp.createShoppingList(store);

        sLHandlertemp.addItemToList(product, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(1, sLHandlertemp.getAllShoppingLists().get(0).getCart().get(0).getProductID());

        sLHandlertemp.removeProduct(product, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(0, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.addItemToList(null, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(0, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.addItemToList(product, null);
        assertEquals(0, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.removeProduct(null, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(0, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.removeProduct(product, null);
        assertEquals(0, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());
    }

    @Test
    public void testCartTotal(){
        sLHandlertemp.createShoppingList(store);
        sLHandlertemp.addItemToList(product, sLHandlertemp.getAllShoppingLists().get(0));

        double total = sLHandlertemp.getCartTotal(sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(13.96, total);

        total = sLHandlertemp.getCartTotal(null);
        assertEquals(-1.0, total);
    }

    @Test
    public void testAllShoppingListsCartTotal(){
        double total = sLHandlertemp.getAllShoppingListTotal();
        assertEquals(71.88, total);
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
    }
}
