package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeInventoryHandlerIT {
    private HomeInventoryHandler hIHandlertemp;
    private HomeProduct apple;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for HomeInventoryHandler");

        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;
        hIHandlertemp = new HomeInventoryHandler(forProduction);

        apple = hIHandlertemp.getHomeProducts().get(0);

        List<String> appleExpiryDate = new ArrayList<>();
        appleExpiryDate.add("2023-11-15");
        appleExpiryDate.add("2024-10-20");
        appleExpiryDate.add("2023-12-31");

        apple.setExpiryDates(appleExpiryDate);
    }

    @Test
    public void testGetStockProducts() {
        //Tests if we can get all stock products (apple has stock 1 to start)
        List<HomeProduct> existStockProducts = hIHandlertemp.getStockProduct();
        assertEquals(1, existStockProducts.size());
    }

    @Test
    public void testSortStockProducts() {
        //Tests if the products being sorted by have/desired is working properly, only apple has stock
        List<HomeProduct> sortedStockProducts = hIHandlertemp.getSortedStockProduct();
        assertEquals(1,sortedStockProducts.size());
        assertEquals("Apple", sortedStockProducts.get(0).getProductName());
    }

    @Test
    public void testGetHiddenProducts() {
        //Tests if we can get all of the hidden products (0 stock, 0 desired stock), 35 prods in total, 34 are hidden
        List<HomeProduct> hiddenProducts = hIHandlertemp.getHiddenProduct();
        assertEquals(34, hiddenProducts.size());
    }

    @Test
    public void testStockIncrementAndDecrement(){
        //Test increment (apple stock starts at 3 in the stub and we increment it to 4)
        hIHandlertemp.incrementStockQuantityBy1(apple);
        assertEquals(2, apple.getHomeProductStockQuantity());

        //Test decrement (apple stock is at 4 from above, should be 3 when we assert)
        hIHandlertemp.decreaseStockQuantityBy1(apple);
        assertEquals(1, apple.getHomeProductStockQuantity());
    }

    @Test
    public void testDesiredQuantityIncrementAndDecrement(){
        //Test increment (apple desired quantity starts at 2 in the stub and we increment it to 3)
        hIHandlertemp.incrementDesiredQuantityBy1(apple);
        assertEquals(1, apple.getHomeProductDesiredQuantity());

        //Test decrement (apple desired quantity is at 3 from above, should be 2 when we assert)
        hIHandlertemp.decreaseDesiredQuantityBy1(apple);
        assertEquals(0, apple.getHomeProductDesiredQuantity());
    }

    @Test
    public void testGetHomeProductExpiryDates() {
        List<String> expiryDates = hIHandlertemp.getHomeProductExpiryDates(apple);

        if (!expiryDates.get(0).equals("2023-11-15") || !expiryDates.get(1).equals("2024-10-20") || !expiryDates.get(2).equals("2023-12-31")) {
            fail();
        }
    }

    @Test
    public void testGetHomeProductExpiryDatesAscending() {
        List<String> expiryDates = hIHandlertemp.getHomeProductSortedExpiryDatesAscending(apple);

        if (!expiryDates.get(0).equals("2023-11-15") || !expiryDates.get(1).equals("2023-12-31") || !expiryDates.get(2).equals("2024-10-20")) {
            fail();
        }
    }

    @Test
    public void testGetHomeProductExpiryDatesDescending() {
        List<String> expiryDates = hIHandlertemp.getHomeProductSortedExpiryDatesDescending(apple);

        if (!expiryDates.get(0).equals("2024-10-20") || !expiryDates.get(1).equals("2023-12-31") || !expiryDates.get(2).equals("2023-11-15")) {
            fail();
        }
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
    }
}

