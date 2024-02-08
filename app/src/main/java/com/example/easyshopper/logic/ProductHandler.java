package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;

import java.util.Comparator;
import java.util.List;

public class ProductHandler {

    private ProductPersistence productPersistence = Services.getProductPersistence();
    private PricePersistence pricePersistence = Services.getPricePersistence();
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

    /*
     * method gets a product by its productId
     */
    public Product getProductByID(int id)
    {
        return productPersistence.getProductById(id);
    }

    /*
    * method gets the price of a product in a store
     */
    public double getPriceOfProductInStore(Product product, Store store)
    {
        return pricePersistence.getPrice(product, store);
    }

    /*
     * returns a list of all the prices sorted in order for a given product
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
