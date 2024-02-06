package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.StorePersistence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ProductHandler {

    private ProductPersistence productPersistence = Services.getProductPersistence();
    private PricePersistence pricePersistence = Services.getPricePersistence();
    private StorePersistence storePersistence = Services.getStorePersistence();

    public ProductHandler() {}

    /*
     * method searches a list for Product and returns the name, or returns null
     */
    public List<Product> getProductsByName(String prodName)
    {
        return productPersistence.getProductsByName(prodName);
    }

    /*
     * method gets a list of all products initialized in the database
     */
    public List<Product> getAllProducts()
    {
        return productPersistence.getExistingProducts();
    }

    /* this method takes
     *
     */
    public Product getProductByID(int id)
    {
        return productPersistence.getProductById(id);
    }

    public double getPriceOfProductInStore(Product product, Store store)
    {
        return pricePersistence.getPrice(product, store);
    }

    /*
     * Im not sure if this actually does anything
     */
    public List<Price> allStoreSortedPrice(Product product)
    {
        List<Price> productPrices = pricePersistence.getAllPricesForSameProduct(product);

        // Sort the list by their price
        productPrices.sort(new Comparator<Price>() {
            @Override
            public int compare(Price p1, Price p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });

        return productPrices;
    }
}
