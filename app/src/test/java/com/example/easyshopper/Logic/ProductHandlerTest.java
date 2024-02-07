package com.example.easyshopper.Logic;

import static org.junit.Assert.assertNotNull;

import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;

import org.junit.Before;
import org.junit.Test;


import java.util.List;


public class ProductHandlerTest {

    private ProductHandler productHandler;
    @Before
    public void setup() {
        System.out.println("Starting test for ProductHandler");
        productHandler = new ProductHandler();
    }




    /*
     * fake prices and products for testing
     */
    Product product1 = new Product(1,"apple",1.1,1.0,1.0);
    Price appleP1 = new Price(1,1,1.00);
    Price appleP2 = new Price(2,1,1.25);
    Price appleP3 = new Price(2,1,0.95);

    Product product2 = new Product(2,"pie",2.1,9.0,2.0);
    Price pieP1 = new Price(1,2,5.00);
    Price pieP2 = new Price(2,2,5.25);
    Price pieP3 = new Price(2,2,4.95);


    @Test
    public void testAllStoreSortedPrice(){
        System.out.println("\nStarting testing testAllStoreSortedPrice");

        List<Price> priceList = productHandler.allStoreSortedPrice(product1);

        assertNotNull(priceList);
        priceList.forEach(System.out::println);
        System.out.println("Finished testAllStoreSortedPrice");
    }


}
