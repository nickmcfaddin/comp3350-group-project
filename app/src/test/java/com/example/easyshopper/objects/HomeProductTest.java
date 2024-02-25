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
        System.out.println("\nStarting testCreateAPrice");

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
        HomeProduct homeProduct = new HomeProduct(1, "Banana", 2, 2, 2, 3, 0, expiryDates);
        HomeProduct invalidHomeProduct = new HomeProduct(1, "Banana", 2, 2, 2, 0, 0, expiryDates);

        //Test GETTER methods
        assertNotNull(homeProduct);
        assertNotNull(invalidHomeProduct);
        assertEquals(1, homeProduct.getProductID());
        assertEquals("Banana", homeProduct.getProductName());
        assertEquals(2, homeProduct.getFat(), 0.00001);
        assertEquals(2, homeProduct.getCarb(), 0.00001);
        assertEquals(2, homeProduct.getProtein(), 0.00001);
        assertEquals(3, homeProduct.getHomeProductStockQuantity());
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());

        assertEquals(0, invalidHomeProduct.getHomeProductStockQuantity());
        assertEquals(0, invalidHomeProduct.getHomeProductExpiryDates().size());

        // Test other methods
        homeProduct.decreaseDesiredQuantityBy1();
        homeProduct.decreaseStockQuantityBy1();
        assertEquals(2, homeProduct.getHomeProductStockQuantity());
        assertEquals(0, homeProduct.getHomeProductDesiredQuantity());

        homeProduct.incrementDesiredQuantityBy1();
        assertEquals(1, homeProduct.getHomeProductDesiredQuantity());

        homeProduct.incrementStockQuantityBy1("2023-11-15");
        assertEquals(3, homeProduct.getHomeProductStockQuantity());

        List<String> expectedExpiryDates = homeProduct.getHomeProductExpiryDates();
        List<String> expectedSortedExpiryDatesAscending = homeProduct.getSortedExpiryDatesAscending();
        List<String> expectedSortedExpiryDatesDescending = homeProduct.getSortedExpiryDatesDescending();

        assertEquals(expiryDates, expectedExpiryDates);
        assertEquals(sortedExpiryDatesAscending, expectedSortedExpiryDatesAscending);
        assertEquals(sortedExpiryDatesDescending, expectedSortedExpiryDatesDescending);

        homeProduct.removeEarliestExpiryDate();
        expiryDates.remove("2023-11-15");
        assertEquals(expiryDates, homeProduct.getHomeProductExpiryDates());

        homeProduct.addExpiryDate("2025-01-01");
        homeProduct.addExpiryDate("2025-13-30");
        homeProduct.addExpiryDate("aaaa-bb-cc");
        homeProduct.addExpiryDate("test");
        expiryDates.add("2025-01-01");
        assertEquals(expiryDates, homeProduct.getHomeProductExpiryDates());

        System.out.println("Finished testCreateAHomeProduct");
    }
}
