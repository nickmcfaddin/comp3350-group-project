package com.example.easyshopper.logic;

import android.util.Log;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ProductHandler implements Serializable {
    private static ProductPersistence productPersistence;
    private static PricePersistence pricePersistence;

    public ProductHandler(boolean forProduction) {
        productPersistence = Services.getProductPersistence(forProduction);
        pricePersistence = Services.getPricePersistence(forProduction);
    }

    /*
     * method searches a list for Product and returns the name, or returns null
     */
    public static List<Product> getProductsByName(String prodName)
    {
        return productPersistence.getProductsByName(prodName);
    }

    /*
     * method gets a list of all products initialized in the database
     */
    public static List<Product> getAllProducts()
    {
        return productPersistence.getExistingProducts();
    }

    /*
     * method gets a product by its productId
     */
    public static Product getProductByID(int id)
    {
        if(id < 0) {
            Log.e("Product Handler", "Invalid product id passed when retrieving product!");
            return null;
        }

        return productPersistence.getProductById(id);
    }

    /*
    * method gets the price of a product in a store
     */
    public static double getPriceOfProductInStore(Product product, Store store)
    {
        if(product == null || store == null) {
            Log.e("Product Handler", "Invalid product/store passed when retrieving price!");
            return -1;
        }

        return pricePersistence.getPrice(product, store);
    }

    /*
     * returns a list of all the prices sorted in order for a given product
     */
    public static List<Price> allStoreSortedPrice(Product product)
    {
        if(product == null) {
            Log.e("Product Handler", "Invalid product passed when retrieving product!");
            return null;
        }

        List<Price> productPrices = pricePersistence.getAllPricesForSameProduct(product);

        // Sort the list by their price
        Collections.sort(productPrices);

        return productPrices;
    }
}
