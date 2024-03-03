package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class ProductHandler implements Serializable {
    private ProductPersistence productPersistence;
    private PricePersistence pricePersistence;

    public ProductHandler(boolean forProduction) {
        productPersistence = Services.getProductPersistence(forProduction);
        pricePersistence = Services.getPricePersistence(forProduction);
    }

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

    /*
     * method gets a product by its productId
     */
    public Product getProductByID(int id)
    {
        if(id < 0) {
            return null;
        }

        return productPersistence.getProductById(id);
    }

    /*
    * method gets the price of a product in a store
     */
    public double getPriceOfProductInStore(Product product, Store store)
    {
        if(product == null || store == null) {
            return -1;
        }

        return pricePersistence.getPrice(product, store);
    }

    /*
     * returns a list of all the prices sorted in order for a given product
     */
    public List<Price> allStoreSortedPrice(Product product)
    {
        if(product == null) {
            return  null;
        }

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
