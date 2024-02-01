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

    //Updates a Product's information
    void updateProduct(int productID, Product newProduct);

    // OPTIONAL: The following 3 functions should be put on hold for now.

    //Add a Product to the overall list of Product's
    void addProduct(Product newProduct);

    //Delete a Product from the overall list of Product's
    void deleteProduct(Product product);

    //Delete a Product from the overall list of Product's by its productID
    void deleteProductByID(int productID);
}
