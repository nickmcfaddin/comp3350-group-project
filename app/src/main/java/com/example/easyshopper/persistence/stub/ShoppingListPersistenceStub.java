package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;
import com.example.easyshopper.persistence.StorePersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ShoppingListPersistenceStub implements ShoppingListPersistence {
    private List<ShoppingList> shoppingListArray;

    public ShoppingListPersistenceStub(){
        this.shoppingListArray = new ArrayList<>();

        Services services = new Services();
        StorePersistence storePersistence = services.getStorePersistence();
        ProductPersistence productPersistence = services.getProductPersistence();

        List<Store> existingStores = storePersistence.getExistingStores();
        List<Product> existingProducts = productPersistence.getExistingProducts();

        for (int i=0; i<existingStores.size(); i++){
            shoppingListArray.add(new ShoppingList(i+1, existingStores.get(i)));
        }

        for (int i=0; i<shoppingListArray.size(); i++){
            ShoppingList indexList = shoppingListArray.get(i);

            for (int j=0; j<existingProducts.size(); j++){
                indexList.addProductToCart(existingProducts.get(j));
            }

            shoppingListArray.set(i, indexList);
        }
    }

    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return Collections.unmodifiableList(shoppingListArray);
    }

    @Override
    public ShoppingList getShoppingListById(int id) {
        for (int i = 0; i < shoppingListArray.size(); i++){
            if (shoppingListArray.get(i).getShoppingListID() == id){
                return shoppingListArray.get(i);
            }
        }
        return null;
    }

    @Override
    public void updateShoppingList(int shoppingListID, ShoppingList newShoppingList) {
        int index = -1;

        for (int i = 0; i < shoppingListArray.size(); i++){
            if (shoppingListArray.get(i).getShoppingListID() == shoppingListID) {
                // productID will not repeat
                index = i;
            }
        }

        if (index != -1) {
            shoppingListArray.set(index, newShoppingList);
        }
    }

    @Override
    public void addShoppingList(ShoppingList shoppingList) {
        for (int i=0; i<shoppingListArray.size(); i++){
            ShoppingList indexShoppingList = shoppingListArray.get(i);

            if ((indexShoppingList.getShoppingListID() == shoppingList.getShoppingListID()) || (Objects.equals(indexShoppingList.getStore().getStoreName(), shoppingList.getStore().getStoreName()))){
                // shoppingList id already existed
                // or adding list of same store
                return;
            }
        }

        shoppingListArray.add(shoppingList);
    }

    @Override
    public void deleteShoppingListById(int shoppingListID) {
        for (int i = 0; i < shoppingListArray.size(); i++){
            if (shoppingListArray.get(i).getShoppingListID() == shoppingListID){
                shoppingListArray.remove(i);
                return;
            }
        }
    }

    @Override
    public double getAllShoppingListTotal() {
        int total = 0;

        for (int i=0; i<shoppingListArray.size(); i++){
            total += shoppingListArray.get(i).getTotalAmount();
        }

        return total;
    }

    @Override
    public double getShoppingListTotalById(int shoppingListID) {
        double total = 0;

        for (int i=0; i < shoppingListArray.size(); i++){
            if (shoppingListArray.get(i).getShoppingListID() == shoppingListID){
                total = shoppingListArray.get(i).getTotalAmount();
            }
        }
        return total;
    }
}
