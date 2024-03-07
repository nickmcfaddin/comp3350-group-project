package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.objects.HomeProduct;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HomeInventoryHandlerTest {
    private HomeProduct apple;
    @Before
    public void setup() {
        System.out.println("Starting test HomeInventoryHandler");

        boolean forProduction = false;
        HomeInventoryHandler homeInventoryHandler = new HomeInventoryHandler(forProduction);

        apple = HomeInventoryHandler.getHomeProducts().get(0);

        List<String> appleExpiryDate = new ArrayList<>();
        appleExpiryDate.add("2023-11-15");
        appleExpiryDate.add("2024-10-20");
        appleExpiryDate.add("2023-12-31");

        apple.setExpiryDates(appleExpiryDate);
    }

    @Test
    public void testGetStockProducts() {
        //Tests if we can get all stock products (4 = # of stock products)
        List<HomeProduct> existStockProducts = HomeInventoryHandler.getStockProduct();
        assertEquals(4, existStockProducts.size());
    }

    @Test
    public void testSortStockProducts() {
        //Tests if the products being sorted by have/desired is working properly
        //The order of the stock products should be <Banana, Apple, Kiwi, Orange>
        List<HomeProduct> sortedStockProducts = HomeInventoryHandler.getSortedStockProduct();
        assertEquals(4,sortedStockProducts.size());
        assertEquals("Banana", sortedStockProducts.get(0).getProductName());
    }

    @Test
    public void testGetHiddenProducts() {
        //Tests if we can get all of the hidden products (0 stock, 0 desired stock)
        List<HomeProduct> hiddenProducts = HomeInventoryHandler.getHiddenProduct();
        assertEquals(3, hiddenProducts.size());

        assertEquals("Peanut", hiddenProducts.get(0).getProductName());
        assertEquals("Pineapple", hiddenProducts.get(1).getProductName());
        assertEquals("Sausage", hiddenProducts.get(2).getProductName());
    }

    @Test
    public void testStockIncrementAndDecrement(){
        //Test increment (apple stock starts at 3 in the stub and we increment it to 4)
        HomeInventoryHandler.incrementStockQuantityBy1(apple);
        assertEquals(4, apple.getHomeProductStockQuantity());

        //Test decrement (apple stock is at 4 from above, should be 3 when we assert)
        HomeInventoryHandler.decreaseStockQuantityBy1(apple);
        assertEquals(3, apple.getHomeProductStockQuantity());
    }

    @Test
    public void testDesiredQuantityIncrementAndDecrement(){
        //Test increment (apple desired quantity starts at 2 in the stub and we increment it to 3)
        HomeInventoryHandler.incrementDesiredQuantityBy1(apple);
        assertEquals(3, apple.getHomeProductDesiredQuantity());

        //Test decrement (apple desired quantity is at 3 from above, should be 2 when we assert)
        HomeInventoryHandler.decreaseDesiredQuantityBy1(apple);
        assertEquals(2, apple.getHomeProductDesiredQuantity());
    }

    @Test
    public void testGetHomeProductExpiryDates() {
        List<String> expiryDates = HomeInventoryHandler.getHomeProductExpiryDates(apple);

        if (!expiryDates.get(0).equals("2023-11-15") || !expiryDates.get(1).equals("2024-10-20") || !expiryDates.get(2).equals("2023-12-31")) {
            fail();
        }
    }

    @Test
    public void testGetHomeProductExpiryDatesAscending() {
        List<String> expiryDates = HomeInventoryHandler.getHomeProductSortedExpiryDatesAscending(apple);

        if (!expiryDates.get(0).equals("2023-11-15") || !expiryDates.get(1).equals("2023-12-31") || !expiryDates.get(2).equals("2024-10-20")) {
            fail();
        }
    }

    @Test
    public void testGetHomeProductExpiryDatesDescending() {
        List<String> expiryDates = HomeInventoryHandler.getHomeProductSortedExpiryDatesDescending(apple);

        if (!expiryDates.get(0).equals("2024-10-20") || !expiryDates.get(1).equals("2023-12-31") || !expiryDates.get(2).equals("2023-11-15")) {
            fail();
        }
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        Services.clean();
    }
}
