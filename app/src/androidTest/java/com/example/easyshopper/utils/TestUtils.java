package com.example.easyshopper.utils;

import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import java.util.List;

public class TestUtils {
    private ProductHandler productHandler;
    private ShoppingListHandler shoppingListHandler;
    private StoreHandler storeHandler;

    public TestUtils(){
        productHandler = new ProductHandler();
        shoppingListHandler = new ShoppingListHandler();
        storeHandler = new StoreHandler();
    }

    //ProductHandler implementations
    public List<Product> getProductsByName(String productName) {return productHandler.getProductsByName(productName);}

    public List<Product> getAllProducts()
    {
        return productHandler.getAllProducts();
    }

    public Product getProductByID(int id)
    {
        return productHandler.getProductByID(id);
    }

    public double getPriceOfProductInStore(Product product, Store store) {return productHandler.getPriceOfProductInStore(product, store);}

    public List<Price> allStoreSortedPrice(Product product){return productHandler.allStoreSortedPrice(product);}


    //ShoppingListHandler implementations
    public String getShoppingListByName(){return shoppingListHandler.getShoppingListByName();}

    public void addItemToList(Product newProduct, ShoppingList shoppingList){shoppingListHandler.addItemToList(newProduct, shoppingList);}

    public void createShoppingList(String shoppingListName, Store store){shoppingListHandler.createShoppingList(shoppingListName, store);}

    public void removeShoppingList(ShoppingList shoppingList){shoppingListHandler.removeShoppingList(shoppingList);}

    public void removeProduct(Product product, ShoppingList shoppingList){shoppingListHandler.removeProduct(product, shoppingList);}


    //StoreHandler implementations
    public Store getStoreById(int storeID)
    {
        return storeHandler.getStoreById(storeID);
    }
}
