package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.ProductPersistence;

import java.util.List;

public class ProductPersistenceStub implements ProductPersistence {
    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return null;
    }

    @Override
    public void setProductPrice(double price) {

    }
}
