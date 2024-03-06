package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

public class ShoppingListHandlerTest {

    // private Product product;
    private Store store;

    @Before
    public void setup(){
        System.out.println("Starting test ShoppingListHandler");
        boolean forProduction = false;

        ShoppingListHandler shoppingListHandler = new ShoppingListHandler(forProduction);
        store = new Store(10, "TestStore");
    }

    @Test
    public void testGetAllShoppingLists() {
        //Tests if we can get all shopping lists (3 = number of lists)
        List<ShoppingList> existLists = ShoppingListHandler.getAllShoppingLists();
        assertEquals(3, existLists.size());
    }

    @Test
    public void testCreateShoppingList() {
        //Create a list with the new store as the parameter
        ShoppingListHandler.createShoppingList(store);

        //Tests that the size of the existing shopping lists reflects the change
        assertEquals(4, ShoppingListHandler.getAllShoppingLists().size());

        ShoppingListHandler.createShoppingList(null);
        assertEquals(4, ShoppingListHandler.getAllShoppingLists().size());
    }

    @Test
    public void testCartTotal(){
        double total = ShoppingListHandler.getCartTotal(ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0.0, total);

        total = ShoppingListHandler.getCartTotal(null);
        assertEquals(-1.0, total);
    }

    @Test
    public void testAllShoppingListsCartTotal(){
        double total = ShoppingListHandler.getAllShoppingListTotal();
        assertEquals(0.0, total);
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");

        Services.clean();
    }
}
