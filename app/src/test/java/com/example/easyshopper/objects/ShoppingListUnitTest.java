package com.example.easyshopper.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ShoppingListUnitTest {

    @Before
    public void setup() {
        System.out.println("Starting test for ShoppingList");
    }

    @Test
    public void testCreateAShoppingList() {
        System.out.println("\nStarting testCreateAShoppingList");

        //Create Store to use for tests
        Store store = new Store(1, "Kwik-E-Mart");

        //Create ShoppingList to use for tests
        ShoppingList shoppingList = new ShoppingList(store);

        //Test GETTER methods
        assertNotNull(store);
        assertEquals("Kwik-E-Mart", shoppingList.getShoppingListName());
        assertEquals(store, shoppingList.getStore());

        assertEquals("Kwik-E-Mart", shoppingList.toString());

        //Create new Product to use for test
        Product product = new Product(1, "Eggs", 0.3, 5, 0.8, 1);

        //Add our new Product's to the ArrayList<Product> to test against
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(product);

        //Test Product addition to ShoppingList
        shoppingList.addProductToCart(product);

        //Testing ShoppingList functions
        assertEquals(productArrayList, shoppingList.getCart());
        assertTrue(shoppingList.checkForProductInCart(product));

        //Test Product removal from ShoppingList
        shoppingList.removeProductFromCart(product);
        assertEquals(0, shoppingList.getCart().size());

        System.out.println("Finished testCreateAShoppingList");
    }
}


