package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.Product;

import java.util.List;

public interface ProductPersistence {
    Product getProductById(int id);
    List<Product> getProductsByName(String name);
    void setProductPrice(double price);
}
