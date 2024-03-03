package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.io.Serializable;
import java.util.List;


//Handles the shopping lists
public class ShoppingListHandler implements Serializable {
    private ShoppingListPersistence shoppingListPersistence;
    private PricePersistence pricePersistence;

    //constructor
    public ShoppingListHandler(boolean forProduction){
        shoppingListPersistence = Services.getShoppingListPersistence(forProduction);
        pricePersistence = Services.getPricePersistence(forProduction);
    }

    public List<ShoppingList> getAllShoppingLists(){
        return shoppingListPersistence.getExistingShoppingLists();
    }

    //add item into the given shopping list
    public void addItemToList(Product newProduct, ShoppingList shoppingList){
        if(!shoppingListPersistence.shoppingListExists(shoppingList) ||
                newProduct == null || shoppingList == null) {

            return;
        }

        shoppingList.addProductToCart(newProduct);
        shoppingListPersistence.updateShoppingList(shoppingList);
    }

    //create a new shopping list and add to overall shopping list array
    public void createShoppingList(Store store){
        //validate inputs
        if(store == null || shoppingListPersistence.listWithStoreExists(store)) {
            return;
        }

        ShoppingList newList = new ShoppingList(store);
        shoppingListPersistence.addShoppingList(newList);
    }

    //remove given shoppingList from overall shopping list array
    public void removeShoppingList(ShoppingList shoppingList){
        if(shoppingList != null && shoppingListPersistence.shoppingListExists(shoppingList)) {
            shoppingListPersistence.deleteShoppingList(shoppingList);
        }
    }

    //remove an item from a shopping list
    public void removeProduct(Product product, ShoppingList shoppingList){
        if(!shoppingListPersistence.shoppingListExists(shoppingList)||
                product == null || shoppingList == null) {
            return;
        }

        shoppingList.removeProductFromCart(product);
        shoppingListPersistence.updateShoppingList(shoppingList);
    }


    //Gives total price of the ShoppingList
    public double getCartTotal(ShoppingList shoppingList){
        if(shoppingList == null || !shoppingListPersistence.shoppingListExists(shoppingList))
        {
            return -1;
        }

        double total = 0;
        Store store = shoppingList.getStore();

        for (Product i : shoppingList.getCart()){
            total += pricePersistence.getPrice(i, store);
        }

        //modify this double value so that it is only two decimal places long
        String formattedNumber = String.format("%.2f", total);
        total = Double.parseDouble(formattedNumber);

        return total;
    }

    //Returns the total price of all Product's on all ShoppingList's combined
    public double getAllShoppingListTotal() {
        double total = 0;
        List<ShoppingList> shoppingLists = shoppingListPersistence.getExistingShoppingLists();

        for (ShoppingList shoppingList : shoppingLists){
            total += getCartTotal(shoppingList);
        }

        return total;
    }
}
