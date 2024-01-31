package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProductPersistenceStub implements ProductPersistence {
    private List<Product> productList;

    public ProductPersistenceStub() {
        this.productList = new ArrayList<>();

        // sort by date added?
        Date date = new Date();

        Store costco = new Store(1, "Costco");
        Store walmart = new Store(2, "Walmart");
        Store superstore = new Store(3, "SuperStore");

        productList.add(new Product(1, "Apple", walmart, 0.5, 0.3, 25, 0.5));
        productList.add(new Product(2, "Apple", costco, 0.6, 0.3, 25, 0.5));
        productList.add(new Product(3, "Kiwi", walmart, 0.5, 0.5, 11, 1));
        productList.add(new Product(4, "Kiwi", superstore, 0.5, 0.3, 25, 0.5));
        productList.add(new Product(5, "Banana", costco, 0.25, 0.3, 27, 1.3));
        productList.add(new Product(6, "Orange", superstore, 0.4, 0.2, 15, 1));
    }

    @Override
    public List<Product> getExistingProducts() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProductById(int productID) {
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductID() == productID){
                return productList.get(i);
            }
        }

        return null;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> newList = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++){
            if (Objects.equals(productList.get(i).getProductName(), name)){
                newList.add(productList.get(i));
            }
        }

        return newList;
    }

    @Override
    public void updateProductPrice(int productID, double price) {
        int index = -1;

        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductID() == productID) {
                // productID will not repeat
                index = i;
            }
        }

        if (index != -1) {
            Product newProduct = productList.get(index);
            newProduct.setPrice(price);

            productList.set(index, newProduct);
        }
    }

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

    // OPTIONAL
    @Override
    public void addProduct(Product newProduct) {
        for (int i=0; i<productList.size(); i++){
            Product indexProduct = productList.get(i);

            if ((indexProduct.getProductID() == newProduct.getProductID()) || (Objects.equals(indexProduct.getProductName(), newProduct.getProductName()) && Objects.equals(indexProduct.getStore(), newProduct.getStore()))){
                // newProduct id already existed
                // or adding same item at the same store
                return;
            }
        }

        productList.add(newProduct);
    }

    @Override
    public void deleteProduct(Product product) {
        int index = productList.indexOf(product);

        if (index < 0){
            // if product doesn't exist
            return;
        }

        productList.remove(index);
    }

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
