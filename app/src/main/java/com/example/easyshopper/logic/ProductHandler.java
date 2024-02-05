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

    public ArrayList<String> sameProductStoreAndPriceList (Product product)
    {
        HashMap<String, Double> map = new HashMap<>();

        List<Store> stores = storePersistence.getExistingStores();

        for (Store store : stores){
            if (pricePersistence.getPrice(product, store) != -1) {
                map.put(store.getStoreName(), pricePersistence.getPrice(product, store));
            }
        }

        Map<String, Double> sortedMap = sortByValue(map);

        ArrayList<String> output = new ArrayList<>();

        for (Map.Entry<String, Double> entry : sortedMap.entrySet()){
            output.add(entry.getKey() + ": " + entry.getValue());
        }

        return output;
    }

    public static HashMap<String, Double> sortByValue(HashMap<String, Double> map)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(map.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /*
    *Methods to implement
    *  List<Price> getAllPricesForSameProduct(int productID);
    *   Get product by name (done)
    *   Get product from id (done)
    *   Get a products price from a store
    *   Get product prices from all stores (sort by ascending order)

     */
}
