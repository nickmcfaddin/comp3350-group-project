package com.example.easyshopper.Logic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;

import org.junit.Before;
import org.junit.Test;


import java.util.Iterator;
import java.util.List;


public class ProductHandlerTest {

    private ProductHandler productHandler;
    @Before
    public void setup() {
        System.out.println("Starting test for ProductHandler");
        productHandler = new ProductHandler();
    }


    @Test
    public void testGetProductsByName() {
        System.out.println("\nStarting testing testGetProductsByName");
        List<Product> testList = productHandler.getProductsByName("Apple");

        assertNotNull(testList);
        assertTrue("Apple is in the list ", testList.contains("Apple"));
        System.out.println("Finished testGetProductsByName");
    }


    @Test
    public void testAllStoreSortedPrice(){
        System.out.println("\nStarting testing testAllStoreSortedPrice");
        ProductPersistence ProductPersistenceStub = new ProductPersistenceStub();
        Product product = ProductPersistenceStub.getProductById(1);
        assertTrue((product.getProductName().equals("Apple")));

        List<Price> priceList = productHandler.allStoreSortedPrice(product);
        assertNotNull(priceList);

        Iterator<Price> iter = priceList.iterator();
        Price current = null, previous = iter.next();
        while(iter.hasNext()){
            current = iter.next();
        }if(previous.getPrice()<current.getPrice()) {
            assertTrue(false);
        }else assertTrue(true);

        priceList.forEach(System.out::println);
        System.out.println("Finished testAllStoreSortedPrice");
    }


}
