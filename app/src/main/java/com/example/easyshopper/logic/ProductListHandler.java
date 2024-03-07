package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.persistence.ProductListPersistence;

import java.io.Serializable;

public class ProductListHandler implements Serializable {
    private static ProductListPersistence productListPersistence;

    public ProductListHandler(boolean forProduction) {
        productListPersistence = Services.getProductListPersistence(forProduction);
    }

    protected static void createList(ProductList productList){
        if(!productListPersistence.listExists(productList)){
            productListPersistence.addList(productList);
        }
    }

    //add item into the given shopping list
    public static void addProductToCart(Product newProduct, ProductList productList){
        if(!productListPersistence.listExists(productList) ||
                newProduct == null || productList == null) {

            return;
        }

        productList.addProductToCart(newProduct);
        productListPersistence.updateList(productList);
    }

    //remove an item from a shopping list
    public static void removeProductFromCart(Product product, ProductList productList){
        if(!productListPersistence.listExists(productList) ||
                product == null || productList == null) {

            return;
        }

        productList.removeProductFromCart(product);
        productListPersistence.updateList(productList);
    }

    public static void clearList(ProductList productList) {
        if(!productListPersistence.listExists(productList)|| productList == null) {
            return;
        }

        productList.getCart().clear();
        productListPersistence.updateList(productList);
    }

    public static void deleteList(ProductList productList) {
        if(!productListPersistence.listExists(productList)|| productList == null) {
            return;
        }

        productListPersistence.deleteList(productList);
    }

    public static boolean listExists(ProductList productList) {
        return productListPersistence.listExists(productList);
    }

}
