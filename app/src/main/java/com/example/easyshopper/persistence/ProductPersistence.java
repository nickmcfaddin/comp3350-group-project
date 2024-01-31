package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;

import java.util.List;

public interface ProductPersistence {
    List<Product> getExistingProducts();
    Product getProductById(int productID);
    List<Product> getProductsByName(String productName);

    void updateProduct(int productID, Product newProduct);

    // OPTIONAL
    void addProduct(Product newProduct);

    void deleteProduct(Product product);

    void deleteProductByID(int productID);
}
