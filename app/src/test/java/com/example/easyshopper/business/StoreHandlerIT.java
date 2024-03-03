package com.example.easyshopper.business;

import static junit.framework.TestCase.assertEquals;

import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.utils.TestUtils;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreHandlerIT {
    private boolean forProduction = true;
    StoreHandler sLHandler;
    private File tempDB;

    @Before
    public void setup() throws IOException{
        System.out.println("Start test StoreHandler");

        this.tempDB = TestUtils.copyDB();
        sLHandler = new StoreHandler(forProduction);
    }

    @Test
    public void testGetStoreByID() {
        assertEquals("Loblaws", sLHandler.getStoreById(1).getStoreName());
    }

    @Test
    public void testGetExistingStores(){
        assertEquals(8, sLHandler.getExistingStores().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
    }
}
