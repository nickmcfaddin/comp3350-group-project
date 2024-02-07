package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;

import java.util.List;

//Implementations are all currently found in ProductPersistenceStub
public interface ProductPersistence {

    //Returns a list of all Product's that exist
    List<Product> getExistingProducts();

    //Returns a single Product type identified by its ProductID
    Product getProductById(int productID);

    //Returns a list of Product's identified by productName
    List<Product> getProductsByName(String productName);
}
