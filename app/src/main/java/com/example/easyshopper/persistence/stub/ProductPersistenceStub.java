package com.example.easyshopper.persistence.stub;

import android.util.Log;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//Product fake db
public class ProductPersistenceStub implements ProductPersistence {
    private List<Product> productList;

    //Constructor
    public ProductPersistenceStub() {
        this.productList = new ArrayList<>();

        productList.add(new Product(1, "Apple", 1.00, 0.3, 0.5));
        productList.add(new Product(2, "Kiwi",  0.5, 11, 1));
        productList.add(new Product(3, "Banana", 0.3, 27, 1.3));
        productList.add(new Product(4, "Orange", 0.2, 15, 1));
    }

    //Get a list of all Product's
    @Override
    public List<Product> getExistingProducts() {
        return Collections.unmodifiableList(productList);
    }

    //Obtain a product using its productID
    public Product getProductById(int productID) {
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductID() == productID){
                return productList.get(i);
            }
        }

        return null;
    }

    //Obtain a product using its productName
    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> newList = new ArrayList<>();
        name = name.toLowerCase();

        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductName().toLowerCase().contains(name)){
                newList.add(productList.get(i));
            }
        }

        return newList;
    }

    //Update a Product's information
    @Override
    public void updateProduct(int productID, Product newProduct){
        int index = -1;

        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductID() == productID) {
                // productID will not repeat
                index = i;
            }
        }

        if (index != -1) {
            productList.set(index, newProduct);
        }
    }

    //OPTIONAL: The following 3 functions should be put on hold for now.

    //Add a new Product to the overall list of Product's
    @Override
    public void addProduct(Product newProduct) {
        for (int i=0; i<productList.size(); i++){
            Product indexProduct = productList.get(i);

            if ((indexProduct.getProductID() == newProduct.getProductID()) || (Objects.equals(indexProduct.getProductName(), newProduct.getProductName()))){
                // newProduct id already existed
                // or adding same item at the same store
                return;
            }
        }

        productList.add(newProduct);
    }

    //Delete a Product from the overall list of Product's
    @Override
    public void deleteProduct(Product product) {
        int index = productList.indexOf(product);

        if (index < 0){
            // if product doesn't exist
            return;
        }

        productList.remove(index);
    }

    //Delete a Product from the overall list of Product's by its productID
    @Override
    public void deleteProductByID(int productID) {
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductID() == productID){
                productList.remove(i);
                return;
            }
        }
    }
}
