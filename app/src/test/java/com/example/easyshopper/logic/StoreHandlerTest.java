package com.example.easyshopper.logic;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.objects.Store;

import org.junit.Before;
import org.junit.Test;

public class StoreHandlerTest {
    @Before
    public void setup(){
        System.out.println("Start test StoreHandler");
    }

    @Test
    public void testGetStoreByID() {
        StoreHandler temp = new StoreHandler();
        assertEquals("Costco",temp.getStoreById(1).getStoreName());
    }

    @Test
    public void testGetExistingStores(){
        StoreHandler temp = new StoreHandler();
        assertEquals(3, temp.getExistingStores().size());
    }
}
