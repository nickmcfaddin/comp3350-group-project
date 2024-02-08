package com.example.easyshopper.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class StoreTest {

    @Before
    public void setup() {System.out.println("Starting test for Store");
    }

    @Test
    public void testCreateAStore() {
        System.out.println("\nStarting testCreateAStore");

        //Create Store to use for tests
        Store store = new Store(19, "Kwik-E-Mart");

        //Test GETTER methods
        assertNotNull(store);
        assertEquals(19, store.getStoreID());
        assertEquals("Kwik-E-Mart", store.getStoreName());

        //Create two new Product's to use for test, one is null which should not be added to the productArrayList
        Product product = new Product(100, "Eggs", 0.3, 5, 0.8);
        Product nullProduct = null;

        //Add our new Product's to the ArrayList<Product> to test against
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(product);

        //Tests our addProductToStore method ensuring we only have the Product with content and not the nullProduct
        store.addProductToStore(product);
        store.addProductToStore(nullProduct);

        System.out.println("Finished testCreateAStore");
    }
}


