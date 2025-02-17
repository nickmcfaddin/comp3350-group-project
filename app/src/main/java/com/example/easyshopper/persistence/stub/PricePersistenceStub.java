package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.StorePersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//For price assigning to products
import java.util.Random;
import java.text.DecimalFormat;

//Price fake db
public class PricePersistenceStub implements PricePersistence, Serializable {
    private List<Price> priceList;

    //Stub Constructor
    public PricePersistenceStub() {
        //List of Prices in the db/stub
        this.priceList = new ArrayList<>();

        //Setup connections to StorePersistence and ProductPersistence stubs
        Services services = new Services();
        StorePersistence storePersistence = services.getStorePersistence(false);
        ProductPersistence productPersistence = services.getProductPersistence(false);

        //Gives us the total quantity of stores and products
        int totalStores = storePersistence.getExistingStores().size();
        int totalProducts = productPersistence.getExistingProducts().size();

        //Iterates over every Store and every Product, assigning a random price to each specific Product in a Store
        // i = storeID, j = productID and both IDs start at 1
        for (int i=1; i<totalStores + 1; i++){
            for (int j=1; j<totalProducts + 1; j++){
                priceList.add(new Price(i, j, i * j + 0.99));
            }
        }
    }

    //Get Price of a single Product (single store)
    public double getPrice(Product product, Store store) {
        for (int i=0; i<priceList.size(); i++){
            if (priceList.get(i).getProductID() == product.getProductID() && priceList.get(i).getStoreID() == store.getStoreID()){
                return priceList.get(i).getPrice();
            }
        }

        return -1;
    }

    //Get Price for a single Product (every store)
    @Override
    public List<Price> getAllPricesForSameProduct(Product product) {
        List<Price> returnList = new ArrayList<>();

        for (int i=0; i<priceList.size(); i++){
            if (priceList.get(i).getProductID() == product.getProductID()){
                returnList.add(priceList.get(i));
            }
        }

        return returnList;
    }
}
