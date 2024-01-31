package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ShoppingListPersistenceStub implements ShoppingListPersistence {
    private List<ShoppingList> shoppingListArray;

    public ShoppingListPersistenceStub(){
        this.shoppingListArray = new ArrayList<>();

        Store costco = new Store(1, "Costco");
        Store walmart = new Store(2, "Walmart");
        Store superstore = new Store(3, "SuperStore");

        ShoppingList costcoList = new ShoppingList(1, costco);
        ShoppingList walmartList = new ShoppingList(2, walmart);
        ShoppingList superStoreList = new ShoppingList(3, superstore);

        costcoList.addItem(new Product(2, "Apple", costco, 0.6, 0.3, 25, 0.5));
        costcoList.addItem(new Product(5, "Banana", costco, 0.25, 0.3, 27, 1.3));
        walmartList.addItem(new Product(1, "Apple", walmart, 0.5, 0.3, 25, 0.5));
        walmartList.addItem(new Product(3, "Kiwi", walmart, 0.5, 0.5, 11, 1));
        superStoreList.addItem(new Product(4, "Kiwi", superstore, 0.5, 0.3, 25, 0.5));
        superStoreList.addItem(new Product(6, "Orange", superstore, 0.4, 0.2, 15, 1));
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
