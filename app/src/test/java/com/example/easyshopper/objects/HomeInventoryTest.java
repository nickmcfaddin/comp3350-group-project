package com.example.easyshopper.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class HomeInventoryTest {
    @Before
    public void setup() {System.out.println("Starting test for HomeInventory");}

    @Test
    public void testCreateAHomeInventory() {
        System.out.println("\nStarting testCreateAHomeInventory");

        List<HomeProduct> allProducts = new ArrayList<>();
        List<String> appleExpiryDate = new ArrayList<>();
        List<String> kiwiExpiryDate = new ArrayList<>();
        List<String> bananaExpiryDate = new ArrayList<>();
        List<String> peanutExpiryDate = new ArrayList<>();
        List<String> orangeExpiryDate = new ArrayList<>();
        appleExpiryDate.add("2023-11-15");
        appleExpiryDate.add("2023-12-31");
        kiwiExpiryDate.add("2023-12-31");
        kiwiExpiryDate.add("2024-01-15");
        bananaExpiryDate.add("2024-01-15");
        peanutExpiryDate.add("2024-02-02");

        HomeProduct apple = new HomeProduct(1, "Apple", 1, 1, 1, 2, 0, appleExpiryDate);
        HomeProduct kiwi = new HomeProduct(2, "Kiwi", 1, 1, 1, 2, 1, kiwiExpiryDate);
        HomeProduct banana = new HomeProduct(3, "Banana", 1, 1, 1, 1, 2, bananaExpiryDate);
        HomeProduct peanut = new HomeProduct(4, "Peanut", 1, 1, 1, 1, 3, peanutExpiryDate);
        HomeProduct orange = new HomeProduct(5, "Orange", 1, 1, 1, 0, 0, orangeExpiryDate);

        allProducts.add(apple);
        allProducts.add(kiwi);
        allProducts.add(banana);
        allProducts.add(peanut);
        allProducts.add(orange);

        //Create HomeInventory to use for tests
        HomeInventory homeInventory = new HomeInventory(allProducts);

        //Test GETTER methods
        assertNotNull(homeInventory);
        assertEquals(5, homeInventory.getAllProducts().size());
        assertEquals(allProducts, homeInventory.getAllProducts());

        // Test other methods
        assertEquals(4, homeInventory.getStockProduct().size());
        assertEquals(1, homeInventory.getHiddenProduct().size());

        List<HomeProduct> sortedStockProduct = homeInventory.getStockProductSorted();

        assertEquals(4, sortedStockProduct.size());
        assertEquals("Peanut", sortedStockProduct.get(0).getProductName());
        assertEquals("Banana", sortedStockProduct.get(1).getProductName());
        assertEquals("Kiwi", sortedStockProduct.get(2).getProductName());
        assertEquals("Apple", sortedStockProduct.get(3).getProductName());

        homeInventory.incrementStockQuantityBy1(kiwi, "2023-01-01");
        sortedStockProduct = homeInventory.getStockProductSorted();
        assertEquals("Peanut", sortedStockProduct.get(0).getProductName());
        assertEquals("Banana", sortedStockProduct.get(1).getProductName());
        assertEquals("Kiwi", sortedStockProduct.get(2).getProductName());
        assertEquals("Apple", sortedStockProduct.get(3).getProductName());

        homeInventory.decreaseStockQuantityBy1(apple);
        List<HomeProduct> hiddenProduct = homeInventory.getHiddenProduct();

        assertEquals(1, hiddenProduct.size());

        homeInventory.incrementDesiredQuantityBy1(banana);
        homeInventory.decreaseDesiredQuantityBy1(peanut);
        sortedStockProduct = homeInventory.getStockProductSorted();
        assertEquals("Banana", sortedStockProduct.get(0).getProductName());
        assertEquals("Peanut", sortedStockProduct.get(1).getProductName());
        assertEquals("Kiwi", sortedStockProduct.get(2).getProductName());

        assertEquals(appleExpiryDate, homeInventory.getHomeProductExpiryDate(apple));
        assertEquals(orangeExpiryDate, homeInventory.getHomeProductExpiryDate(orange));

        List<String> appleSortedExpiryDateAscending = apple.getSortedExpiryDatesAscending();
        List<String> appleSortedExpiryDateDescending = apple.getSortedExpiryDatesDescending();

        assertEquals(appleSortedExpiryDateAscending, homeInventory.getHomeProductSortedExpiryDateAscending(apple));
        assertEquals(appleSortedExpiryDateDescending, homeInventory.getHomeProductSortedExpiryDateDescending(apple));

        System.out.println("Finished testCreateAHomeInventory");
    }
}
