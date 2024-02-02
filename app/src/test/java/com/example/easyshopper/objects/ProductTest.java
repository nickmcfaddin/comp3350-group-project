package com.example.easyshopper.objects;

import com.example.easyshopper.objects.Product;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Before
    public void setup() {System.out.println("Starting test for Product");
    }

    @Test
    public void testCreateAProduct() {
        System.out.println("\nStarting testCreateAProduct");

        //Create Product to use for tests
        Product product = new Product(100, "Eggs", 0.3, 5, 0.8);

        //Tests all GETTER methods
        assertNotNull(product);
        assertEquals(100, product.getProductID());
        assertEquals("Eggs", product.getProductName());
        assertEquals(0.3, product.getFat(), 0.00001);
        assertEquals(5, product.getCarb(), 0.00001);
        assertEquals(0.8, product.getProtein(), 0.00001);

        /*
        * Calories calculation are, calories = fat*9 + carb*4 + protein*4.
        * This is equal to 0.3*9 + 5*4 + 0.8*4 = 25.9
         */
        assertEquals(25.9, product.getCalories(), 0.00001);


        System.out.println("Finished testCreateAProduct");
    }
}

