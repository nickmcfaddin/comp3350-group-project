package com.example.easyshopper.logic;

import android.util.Log;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.persistence.ProductListPersistence;

import java.io.Serializable;
import java.util.UUID;

public class ProductListHandler implements Serializable {
    private static ProductListPersistence productListPersistence;

    public ProductListHandler(boolean forProduction) {
        productListPersistence = Services.getProductListPersistence(forProduction);
    }

    protected static void createList(ProductList productList){
        if(!productListPersistence.listExists(productList)){
            productListPersistence.addList(productList);
        } else {
            Log.e("ProductListHandler", "Can not duplicate existing list!");
        }
    }

    //add item into the given shopping list
    public static void addProductToCart(Product newProduct, ProductList productList){
        if(!productListPersistence.listExists(productList) ||
                newProduct == null || productList == null) {
            Log.e("ProductListHandler", "Invalid list/product passed, can not add to list!");
            return;
        }

        productList.addProductToCart(newProduct);
        productListPersistence.updateList(productList);
    }

    //remove an item from a shopping list
    public static void removeProductFromCart(Product product, ProductList productList){
        if(!productListPersistence.listExists(productList) ||
                product == null || productList == null) {
            Log.e("ProductListHandler", "Invalid list/product passed, can not remove from list!");
            return;
        }

        productList.removeProductFromCart(product);
        productListPersistence.updateList(productList);
    }

    public static void clearList(ProductList productList) {
        if(!productListPersistence.listExists(productList)|| productList == null) {
            Log.e("ProductListHandler", "Invalid list passed, can not clear list!");
            return;
        }

        productList.getCart().clear();
        productListPersistence.updateList(productList);
    }

    public static void deleteList(ProductList productList) {
        if(!productListPersistence.listExists(productList)|| productList == null) {
            Log.e("ProductListHandler", "Invalid list passed, can not delete list!");
            return;
        }

        productListPersistence.deleteList(productList);
    }

    public static boolean listExists(ProductList productList) {
        return productListPersistence.listExists(productList);
    }

}
