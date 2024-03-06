package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.ProductListPersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.StorePersistence;
import com.example.easyshopper.persistence.UserPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListPersistenceStub implements Serializable, ProductListPersistence {
    List<ProductList> productLists;
    List<ShoppingList> shoppingLists;
    List<RequestList> requestLists;

    public ProductListPersistenceStub() {
        productLists = new ArrayList<>();
        shoppingLists = new ArrayList<>();
        requestLists = new ArrayList<>();

        StorePersistence storePersistence = Services.getStorePersistence(false);
        UserPersistence userPersistence = Services.getUserPersistence(false);
        ProductPersistence productPersistence = Services.getProductPersistence(false);

        List<User> existingUsers = userPersistence.getExistingUsers();
        List<Store> existingStores = storePersistence.getExistingStores();
        List<Product> existingProducts = productPersistence.getExistingProducts();

        for (int i=0; i < existingUsers.size(); i++){
            RequestList newRequestList = new RequestList(existingUsers.get(i));

            productLists.add(newRequestList);
            requestLists.add(newRequestList);
        }

        for (int i=0; i<existingStores.size(); i++){
            ShoppingList newShoppingList = new ShoppingList(existingStores.get(i));

            productLists.add(newShoppingList);
            shoppingLists.add(newShoppingList);
        }

        for (int i=0; i<requestLists.size(); i++){
            RequestList indexList = requestLists.get(i);

            for (int j=0; j<existingProducts.size(); j++){
                indexList.addProductToCart(existingProducts.get(j));
            }
        }

    }

    @Override
    public void add(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
    }

    @Override
    public void add(RequestList requestList) {
        requestLists.add(requestList);
    }

    @Override
    public void delete(ShoppingList shoppingList) {
        shoppingLists.remove(shoppingList);
    }

    @Override
    public void delete(RequestList requestList) {
        requestLists.remove(requestList);
    }

    @Override
    public void addList(ProductList productList) {
        productLists.add(productList);
        productList.add(this);
    }

    @Override
    public void updateList(ProductList productList) {
        //java does this for you already
    }

    @Override
    public void deleteList(ProductList productList) {
        productLists.remove(productList);
        productList.delete(this);
    }

    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return shoppingLists;
    }

    @Override
    public List<RequestList> getExistingRequestLists() {
        return requestLists;
    }

    @Override
    public boolean listExists(ProductList queryList) {
        for(ProductList productList : productLists) {
            if(productList.getListID().equals(queryList.getListID())) {
                return true;
            }
        }

        return false;
    }
}
