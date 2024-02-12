package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.ProductPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Product fake db
public class ProductPersistenceStub implements ProductPersistence, Serializable {
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

        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductName().toLowerCase().contains(name.toLowerCase())){
                newList.add(productList.get(i));
            }
        }

        return newList;
    }
}
