package com.example.easyshopper.objects;

import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Price;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ShoppingListUnitTest {

    @Before
    public void setup() {System.out.println("Starting test for ShoppingList");
    }

    @Test
    public void testCreateAShoppingList() {
        System.out.println("\nStarting testCreateAShoppingList");

        //Create Store to use for tests
        Store store = new Store(19, "Kwik-E-Mart");

        //Create ShoppingList to use for tests
        ShoppingList shoppingList = new ShoppingList(25, store);

        //Test GETTER methods
        assertNotNull(store);
        assertEquals(25, shoppingList.getShoppingListID());
        assertEquals(store, shoppingList.getStore());

        //Tests our empty list method
        assertEquals(true, shoppingList.isEmpty());

        //Create new Product to use for test
        Product product = new Product(100, "Eggs", 0.3, 5, 0.8);

        //Add our new Product's to the ArrayList<Product> to test against
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(product);

        //Test Product addition to ShoppingList
        shoppingList.addProductToCart(product);

        //Create Price to use for tests
        Price price = new Price(19, 100, 150);

        //Testing ShoppingList functions
        assertEquals(productArrayList, shoppingList.getItemList());
        assertEquals(true, shoppingList.checkForProductInCart("Eggs"));
        assertEquals(150, shoppingList.cartTotal(), 0.00001);

        //Test Product removal from ShoppingList
        shoppingList.removeProductFromCart(product);
        assertEquals(null, shoppingList);

        System.out.println("Finished testCreateAShoppingList");
    }
}


