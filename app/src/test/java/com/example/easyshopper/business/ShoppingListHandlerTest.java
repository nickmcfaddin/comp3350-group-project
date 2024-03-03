package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;

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
        boolean forProduction = false;

        sLHandlertemp = new ShoppingListHandler(forProduction);
        product = new Product(10, "TestProduct", 0.1, 0.2, 0.3, 1);
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
        StorePersistence storePersistence = new StorePersistenceStub();
        storePersistence.addStore(store);

        //Create a list with the new store as the parameter
        sLHandlertemp.createShoppingList(store);

        //Tests that the size of the existing shopping lists reflects the change
        assertEquals(4, sLHandlertemp.getAllShoppingLists().size());

        sLHandlertemp.removeShoppingList(Services.getShoppingListPersistence(false).getExistingShoppingLists().get(0));
        assertEquals(3, Services.getShoppingListPersistence(false).getExistingShoppingLists().size());

        sLHandlertemp.createShoppingList(null);
        assertEquals(3, Services.getShoppingListPersistence(false).getExistingShoppingLists().size());

        sLHandlertemp.removeShoppingList(null);
        assertEquals(3, Services.getShoppingListPersistence(false).getExistingShoppingLists().size());
    }

    @Test
    public void testItemAddAndRemove() {
        sLHandlertemp.addItemToList(product, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(10, sLHandlertemp.getAllShoppingLists().get(0).getCart().get(4).getProductID());

        sLHandlertemp.removeProduct(product, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(4, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.addItemToList(null, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(4, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.addItemToList(product, null);
        assertEquals(4, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.removeProduct(null, sLHandlertemp.getAllShoppingLists().get(0));
        assertEquals(4, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());

        sLHandlertemp.removeProduct(product, null);
        assertEquals(4, sLHandlertemp.getAllShoppingLists().get(0).getCart().size());
    }

    @Test
    public void testCartTotal(){
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

}
