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
import com.example.easyshopper.persistence.stub.ProductPersistenceStub;
import com.example.easyshopper.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ProductHandlerIT {

    private ProductHandler productHandler;
    private StoreHandler storeHandler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for ProductHandler");
        Services.clean();
        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;

        productHandler = new ProductHandler(forProduction);
        storeHandler = new StoreHandler(forProduction);
    }

    @Test
    public void testGetProductByID(){
        System.out.println("\nStarting testing testGetProductByID");

        assertEquals(ProductHandler.getProductByID(1).getProductName(), "Apple");
        assertNull(ProductHandler.getProductByID(-1));
        System.out.println("Finished testGetProductByID");
    }

    @Test
    public void testGetProductsByName() {
        System.out.println("\nStarting testing testGetProductsByName");
        List<Product> testList = ProductHandler.getProductsByName("Apple");
        assertEquals("Apple", testList.get(0).getProductName());
        System.out.println("Finished testGetProductsByName");
    }

    @Test
    public void testGetProductPriceByStore() {
        Store store = StoreHandler.getExistingStores().get(0);
        Product product = ProductHandler.getAllProducts().get(0);

        assertEquals(0.5, ProductHandler.getPriceOfProductInStore(product,store), .01);

        assertEquals(-1, ProductHandler.getPriceOfProductInStore(null,store), .01);

        assertEquals(-1, ProductHandler.getPriceOfProductInStore(product,null), .01);

    }
    @Test
    public void testAllStoreSortedPrice(){
        System.out.println("\nStarting testing testAllStoreSortedPrice");

        ProductPersistence productPersistence = new ProductPersistenceStub();
        Product product = productPersistence.getProductById(1);
        assertEquals("Apple", product.getProductName());

        List<Price> priceList = ProductHandler.allStoreSortedPrice(product);

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

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }
}

