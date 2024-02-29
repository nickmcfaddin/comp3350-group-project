package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.objects.HomeProduct;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HomeInventoryHandlerTest {
    private HomeInventoryHandler hIHandlertemp;
    private HomeProduct apple;
    @Before
    public void setup() {
        System.out.println("Starting test HomeInventoryHandler");

        boolean forProduction = false;
        hIHandlertemp = new HomeInventoryHandler(forProduction);

        List<String> appleExpiryDate = new ArrayList<>();
        appleExpiryDate.add("2023-11-15");
        appleExpiryDate.add("2024-10-20");
        appleExpiryDate.add("2023-12-31");
        apple = new HomeProduct(1, "Apple", 1.00, 0.3, 0.5, 3, 2, 7, appleExpiryDate);
    }

    @Test
    public void testGetStockProducts() {
        //Tests if we can get all stock products (4 = # of stock products)
        List<HomeProduct> existStockProducts = hIHandlertemp.getStockProduct();
        assertEquals(4, existStockProducts.size());
    }

    @Test
    public void testSortStockProducts() {
        //Tests if the products being sorted by have/desired is working properly
        //The order of the stock products should be <Banana, Apple, Kiwi, Orange>
        List<HomeProduct> sortedStockProducts = hIHandlertemp.getSortedStockProduct();
        assertEquals(4,sortedStockProducts.size());
        assertEquals("Banana", sortedStockProducts.get(0).getProductName());
    }

    @Test
    public void testGetHiddenProducts() {
        //Tests if we can get all of the hidden products (0 stock, 0 desired stock)
        List<HomeProduct> hiddenProducts = hIHandlertemp.getHiddenProduct();
        assertEquals(3, hiddenProducts.size());

        assertEquals("Peanut", hiddenProducts.get(0).getProductName());
        assertEquals("Pineapple", hiddenProducts.get(1).getProductName());
        assertEquals("Sausage", hiddenProducts.get(2).getProductName());
    }

    @Test
    public void testStockIncrementAndDecrement(){
        //Test increment (apple stock starts at 3 in the stub and we increment it to 4)
        hIHandlertemp.incrementStockQuantityBy1(apple);
        assertEquals(4, hIHandlertemp.getStockProduct().size());

        //Test decrement (apple stock is at 4 from above, should be 3 when we assert)
        hIHandlertemp.decreaseStockQuantityBy1(apple);
        assertEquals(3, hIHandlertemp.getStockProduct().size());
    }

    @Test
    public void testDesiredQuantityIncrementAndDecrement(){
        //Test increment (apple desired quantity starts at 2 in the stub and we increment it to 3)
        hIHandlertemp.incrementDesiredQuantityBy1(apple);
        assertEquals(3, hIHandlertemp.getStockProduct().size());

        //Test decrement (apple desired quantity is at 3 from above, should be 2 when we assert)
        hIHandlertemp.decreaseDesiredQuantityBy1(apple);
        assertEquals(2, hIHandlertemp.getStockProduct().size());
    }

    @Test
    public void testGetHomeProductExpiryDates() {
        List<String> expiryDates = hIHandlertemp.getHomeProductExpiryDates(apple);

        for (int i = 0; i < expiryDates.size(); i++) {
            if (expiryDates.get(i) != "2023-11-15" || expiryDates.get(i) != "2024-10-20" || expiryDates.get(i) != "2023-12-31") {
                fail();
            }
        }
    }

    @Test
    public void testGetHomeProductExpiryDatesAscending() {
        List<String> expiryDates = hIHandlertemp.getHomeProductSortedExpiryDatesAscending(apple);

        if (expiryDates.get(0) != "2023-11-15" || expiryDates.get(1) != "2023-12-31" || expiryDates.get(2) != "2024-10-20") {
            fail();
        }
    }

    @Test
    public void testGetHomeProductExpiryDatesDescending() {
        List<String> expiryDates = hIHandlertemp.getHomeProductSortedExpiryDatesDescending(apple);

        if (expiryDates.get(0) != "2024-10-20" || expiryDates.get(1) != "2023-12-31" || expiryDates.get(2) != "2023-11-15") {
            fail();
        }
    }
}
