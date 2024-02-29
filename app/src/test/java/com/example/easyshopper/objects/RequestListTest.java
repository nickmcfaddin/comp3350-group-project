package com.example.easyshopper.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RequestListTest {
    @Before
    public void setup() {System.out.println("Starting test for RequestList");
    }

    @Test
    public void testCreateAShoppingList() {
        System.out.println("\nStarting testCreateARequestList");

        //Create Store to use for tests
        User user = new User("Jack", 1);

        //Create ShoppingList to use for tests
        RequestList requestList = new RequestList(user);

        //Test GETTER methods
        assertNotNull(user);
        assertEquals("Jack", requestList.getUserName());
        assertEquals(user, requestList.getUser());

        //Create new Product to use for test
        Product product = new Product(1, "Eggs", 0.3, 5, 0.8, 1);

        //Add our new Product's to the ArrayList<Product> to test against
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(product);

        //Test Product addition to ShoppingList
        requestList.addProductToCart(product);

        //Testing ShoppingList functions
        assertEquals(productArrayList, requestList.getCart());
        assertTrue(requestList.checkForProductInCart(product));

        //Test Product removal from ShoppingList
        requestList.removeProductFromCart(product);
        assertEquals(0, requestList.getCart().size());

        System.out.println("Finished testCreateARequestListList");
    }

}
