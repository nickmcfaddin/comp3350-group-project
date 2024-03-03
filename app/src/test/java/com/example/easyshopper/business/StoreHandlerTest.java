package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.logic.StoreHandler;

import org.junit.Before;
import org.junit.Test;

public class StoreHandlerTest {
    private boolean forProduction = false;

    @Before
    public void setup(){
        System.out.println("Start test StoreHandler");
    }

    @Test
    public void testGetStoreByID() {
        StoreHandler temp = new StoreHandler(forProduction);
        assertEquals("Costco",temp.getStoreById(1).getStoreName());
    }

    @Test
    public void testGetExistingStores(){
        StoreHandler temp = new StoreHandler(forProduction);
        assertEquals(3, temp.getExistingStores().size());
    }
}
