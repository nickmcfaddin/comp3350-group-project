package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.stub.StorePersistenceStub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductHandler {

    private ProductPersistence productPersistence;
    private PricePersistence pricePersistence ;
    private StorePersistence storePersistence;

    Services services = new Services();

    public ProductHandler() {pricePersistence=services.getPricePersistence();}

    /*
     * method searches a list for Product and returns the name, or returns
     * exception "Not in list"
     */
    public String getProductsByName(String prodName, List<Product> list)
    {
        try {
            return productPersistence.getProductsByName(prodName).toString();
        }catch (Exception e) {
            return "Not in list";
        }
    }


    /* this method takes
     *
     */
    public Product getProductByID(int id)
    {
        try{
            return productPersistence.getProductById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public double getPriceOfProductInStore(Product findProd, Store storeName, List<Product> storeList)
    {
        double found = 0.00;
        int prodId = findProd.getProductID();
        int storeId = storeName.getStoreID();

       if(storeList.contains(findProd))
           return pricePersistence.getPrice(prodId, storeId);
       else
           return found;
    }
/*
 * Im not sure if this actually does anything
 */
    public List<Price> allStoreSortedPrice(List<Price> priceList, int productId)
    {
        List<Price> productAscOrder ;
        productAscOrder=pricePersistence.getAllPricesForSameProduct(productId);
        return productAscOrder;
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
