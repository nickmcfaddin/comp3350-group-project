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

public class ShoppingListPersistenceStub implements ShoppingListPersistence {
    private List<ShoppingList> shoppingListArray;

    //Constructor
    public ShoppingListPersistenceStub(){
        this.shoppingListArray = new ArrayList<>();

        //Setup connections to StorePersistence and ProductPersistence stubs
        Services services = new Services();
        StorePersistence storePersistence = services.getStorePersistence();
        ProductPersistence productPersistence = services.getProductPersistence();

        List<Store> existingStores = storePersistence.getExistingStores();
        List<Product> existingProducts = productPersistence.getExistingProducts();

        for (int i=0; i<existingStores.size(); i++){
            shoppingListArray.add(new ShoppingList(existingStores.get(i)));
        }

        for (int i=0; i<shoppingListArray.size(); i++){
            ShoppingList indexList = shoppingListArray.get(i);

            for (int j=0; j<existingProducts.size(); j++){
                indexList.addProductToCart(existingProducts.get(j));
            }

            shoppingListArray.set(i, indexList);
        }
    }

    //Returns all currently existing ShoppingList's
    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return shoppingListArray;
    }

    //Updates the ShoppingList's information
    @Override
    public void updateShoppingList(ShoppingList newShoppingList) {
        int index = -1;

        for (int i = 0; i < shoppingListArray.size(); i++){
            if (shoppingListArray.get(i).getShoppingListID().equals(newShoppingList.getShoppingListID())) {
                // productID will not repeat
                index = i;
            }
        }

        if (index != -1) {
            shoppingListArray.set(index, newShoppingList);
        }
    }

    //Adds a ShoppingList to the overall ShoppingList array
    @Override
    public void addShoppingList(ShoppingList shoppingList) {
        for (int i=0; i<shoppingListArray.size(); i++){
            ShoppingList indexShoppingList = shoppingListArray.get(i);

            if ((indexShoppingList.getShoppingListID().equals(shoppingList.getShoppingListID())) || shoppingList.getStore().getStoreID() == indexShoppingList.getStore().getStoreID()){
                // shoppingList id already existed
                // or shopping list with store already exists
                return;
            }
        }
        shoppingListArray.add(shoppingList);
    }

    //Delete a ShoppingList from the overall ShoppingList array by it's shoppingListID
    @Override
    public void deleteShoppingList(ShoppingList shoppingList) {
        for (int i = 0; i < shoppingListArray.size(); i++){
            if (shoppingListArray.get(i).getShoppingListID().equals(shoppingList.getShoppingListID())){
                shoppingListArray.remove(i);
                return;
            }
        }
    }
}
