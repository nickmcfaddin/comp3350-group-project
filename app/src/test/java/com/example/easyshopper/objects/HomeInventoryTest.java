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

        ArrayList<HomeProduct> allProducts = new ArrayList<>();
        HomeProduct apple = new HomeProduct(1, "Apple", 1, 1, 1, 1, 0);
        HomeProduct kiwi = new HomeProduct(2, "Kiwi", 1, 1, 1, 0, 1);
        HomeProduct banana = new HomeProduct(3, "Banana", 1, 1, 1, 1, 2);
        HomeProduct peanut = new HomeProduct(4, "Peanut", 1, 1, 1, 1, 3);
        HomeProduct orange = new HomeProduct(5, "Orange", 1, 1, 1, 0, 0);

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
        assertEquals("Kiwi", sortedStockProduct.get(0).getProductName());
        assertEquals("Peanut", sortedStockProduct.get(1).getProductName());
        assertEquals("Banana", sortedStockProduct.get(2).getProductName());
        assertEquals("Apple", sortedStockProduct.get(3).getProductName());

        homeInventory.incrementStockQuantityBy1(kiwi);
        sortedStockProduct = homeInventory.getStockProductSorted();
        assertEquals("Peanut", sortedStockProduct.get(0).getProductName());
        assertEquals("Banana", sortedStockProduct.get(1).getProductName());
        assertEquals("Kiwi", sortedStockProduct.get(2).getProductName());
        assertEquals("Apple", sortedStockProduct.get(3).getProductName());

        homeInventory.decreaseStockQuantityBy1(apple);
        List<HomeProduct> hiddenProduct = homeInventory.getHiddenProduct();

        assertEquals(2, hiddenProduct.size());

        homeInventory.incrementDesiredQuantityBy1(banana);
        homeInventory.decreaseDesiredQuantityBy1(peanut);
        sortedStockProduct = homeInventory.getStockProductSorted();
        assertEquals("Banana", sortedStockProduct.get(0).getProductName());
        assertEquals("Peanut", sortedStockProduct.get(1).getProductName());
        assertEquals("Kiwi", sortedStockProduct.get(2).getProductName());

        System.out.println("Finished testCreateAHomeInventory");
    }
}
