package com.example.easyshopper.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HomeProductTest {
    @Before
    public void setup() {System.out.println("Starting test for HomeProduct");}

    @Test
    public void testCreateAHomeProduct() {
        System.out.println("\nStarting testCreateAPrice");

        //Create HomeProduct to use for tests
        HomeProduct homeProduct = new HomeProduct(1, "Banana", 2, 2, 2, 0, 0);

        //Test GETTER methods
        assertNotNull(homeProduct);
        assertEquals(1, homeProduct.getProductID());
        assertEquals("Banana", homeProduct.getProductName());
        assertEquals(2, homeProduct.getFat(), 0.00001);
        assertEquals(2, homeProduct.getCarb(), 0.00001);
        assertEquals(2, homeProduct.getProtein(), 0.00001);
        assertEquals(0, homeProduct.getHomeProductStockQuantity());
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());

        // Test other methods
        homeProduct.decreaseDesiredQuantityBy1();
        homeProduct.decreaseStockQuantityBy1();
        assertEquals(0, homeProduct.getHomeProductStockQuantity());
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());

        homeProduct.incrementDesiredQuantityBy1();
        assertEquals(1, homeProduct.getHomeProductDesiredQuantity());
        homeProduct.decreaseDesiredQuantityBy1();
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());

        homeProduct.incrementStockQuantityBy1();
        assertEquals(1, homeProduct.getHomeProductStockQuantity());
        homeProduct.decreaseStockQuantityBy1();
        assertEquals(0, homeProduct.getHomeProductStockQuantity());

        System.out.println("Finished testCreateAHomeProduct");
    }
}
