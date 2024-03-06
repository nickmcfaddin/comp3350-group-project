package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.StoreHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreHandlerTest {
    @Before
    public void setup(){
        System.out.println("Start test StoreHandler");

        boolean forProduction = false;
        StoreHandler storeHandler = new StoreHandler(forProduction);
    }

    @Test
    public void testA_GetStoreByID() {
        assertEquals("Costco", StoreHandler.getStoreById(1).getStoreName());
    }

    @Test
    public void testB_GetExistingStores(){
        assertEquals(3, StoreHandler.getExistingStores().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");

        Services.clean();
    }
}
