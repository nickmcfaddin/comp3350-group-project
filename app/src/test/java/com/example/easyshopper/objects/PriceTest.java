package com.example.easyshopper.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PriceTest {

    @Before
    public void setup() {System.out.println("Starting test for Price");
    }

    @Test
    public void testCreateAPrice() {
        System.out.println("\nStarting testCreateAPrice");

        //Create Price to use for tests
        Price price = new Price(13, 13, 2.99);

        //Test GETTER methods
        assertNotNull(price);
        assertEquals(13, price.getStoreID());
        assertEquals(13, price.getProductID());
        assertEquals(2.99, price.getPrice(), 0.00001);

        System.out.println("Finished testCreateAPrice");

    }
}


