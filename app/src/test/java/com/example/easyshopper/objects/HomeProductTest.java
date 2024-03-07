package com.example.easyshopper.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class HomeProductTest {
    @Before
    public void setup() {System.out.println("Starting test for HomeProduct");}

    @Test
    public void testCreateAHomeProduct() {
        System.out.println("\nStarting testCreateHomeProducts");

        List<String> expiryDates = new ArrayList<>();
        expiryDates.add("2023-12-31");
        expiryDates.add("2023-11-15");
        expiryDates.add("2024-01-15");

        List<String> sortedExpiryDatesDescending = new ArrayList<>();
        sortedExpiryDatesDescending.add("2024-01-15");
        sortedExpiryDatesDescending.add("2023-12-31");
        sortedExpiryDatesDescending.add("2023-11-15");

        List<String> sortedExpiryDatesAscending = new ArrayList<>();
        sortedExpiryDatesAscending.add("2023-11-15");
        sortedExpiryDatesAscending.add("2023-12-31");
        sortedExpiryDatesAscending.add("2024-01-15");

        //Create HomeProduct to use for tests
        HomeProduct homeProduct = new HomeProduct(1, "Banana", 2, 2, 2, 3, 0, 14, expiryDates);
        HomeProduct invalidHomeProduct = new HomeProduct(1, "Banana", 2, 2, 2, 0, 0, 12, expiryDates);

        //Test expiry date sorting
        assertNotNull(homeProduct);
        assertNotNull(invalidHomeProduct);

        List<String> expectedExpiryDates = homeProduct.getHomeProductExpiryDates();
        List<String> expectedSortedExpiryDatesAscending = homeProduct.getSortedExpiryDatesAscending();
        List<String> expectedSortedExpiryDatesDescending = homeProduct.getSortedExpiryDatesDescending();

        assertEquals(expiryDates, expectedExpiryDates);
        assertEquals(sortedExpiryDatesAscending, expectedSortedExpiryDatesAscending);
        assertEquals(sortedExpiryDatesDescending, expectedSortedExpiryDatesDescending);

        //Test GETTER methods
        assertEquals(1, homeProduct.getProductID());
        assertEquals("Banana", homeProduct.getProductName());
        assertEquals(2, homeProduct.getFat(), 0.00001);
        assertEquals(2, homeProduct.getCarb(), 0.00001);
        assertEquals(2, homeProduct.getProtein(), 0.00001);
        assertEquals(3, homeProduct.getHomeProductStockQuantity());
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());
        assertEquals(0, invalidHomeProduct.getHomeProductStockQuantity());
        assertEquals(14, homeProduct.getLifeTimeDays());
        assertEquals(12, invalidHomeProduct.getLifeTimeDays());
        assertEquals(0, invalidHomeProduct.getHomeProductExpiryDates().size());

        // Test decrementing and incrementing methods that will be used by +/- in UI
        homeProduct.decreaseDesiredQuantityBy1();
        homeProduct.decreaseStockQuantityBy1();
        assertEquals(2, homeProduct.getHomeProductStockQuantity());
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());

        homeProduct.incrementDesiredQuantityBy1();
        assertEquals(1, homeProduct.getHomeProductDesiredQuantity());
        homeProduct.incrementStockQuantityBy1();
        assertEquals(3, homeProduct.getHomeProductStockQuantity());

        System.out.println("Finished testCreateAHomeProduct");
    }
}
