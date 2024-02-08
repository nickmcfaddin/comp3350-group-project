package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.ProductPersistence;

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

    @Test
    public void testGetProductByID(){
        System.out.println("\nStarting testing testGetProductByID");
        assertEquals(productHandler.getProductByID(1).getProductName(), "Apple");
        System.out.println("Finished testGetProductByID");
    }
    @Test
    public void testGetProductsByName() {
        System.out.println("\nStarting testing testGetProductsByName");
        List<Product> testList = productHandler.getProductsByName("Apple");
        assertEquals("Apple", testList.get(0).getProductName());
        System.out.println("Finished testGetProductsByName");
    }


    @Test
    public void testAllStoreSortedPrice(){
        System.out.println("\nStarting testing testAllStoreSortedPrice");

        ProductPersistence productPersistence = Services.getProductPersistence();
        Product product = productPersistence.getProductById(1);
        assertEquals("Apple", product.getProductName());

        List<Price> priceList = productHandler.allStoreSortedPrice(product);

        double previousPrice = 0;
        for (Price price : priceList)
        {
            if (previousPrice > price.getPrice()){
                fail();
            }
            previousPrice = price.getPrice();
        }

        System.out.println("Finished testAllStoreSortedPrice");
    }
}
