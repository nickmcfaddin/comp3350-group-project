package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductPersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ProductHandlerTest {

    private ProductHandler productHandler;
    private StoreHandler storeHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for ProductHandler");
        productHandler = new ProductHandler();
        storeHandler = new StoreHandler();
    }

    @Test
    public void testGetProductByID(){
        System.out.println("\nStarting testing testGetProductByID");

        assertEquals(productHandler.getProductByID(1).getProductName(), "Apple");
        assertNull(productHandler.getProductByID(-1));
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
    public void testGetProductPriceByStore() {
        Store store = storeHandler.getExistingStores().get(0);
        Product product = productHandler.getAllProducts().get(0);

        assertEquals(1.99,productHandler.getPriceOfProductInStore(product,store), .01);

        assertEquals(-1,productHandler.getPriceOfProductInStore(null,store), .01);

        assertEquals(-1,productHandler.getPriceOfProductInStore(product,null), .01);

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
