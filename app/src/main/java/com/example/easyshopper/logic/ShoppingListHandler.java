package com.example.easyshopper.logic;

import android.util.Log;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.exceptions.InvalidShoppingListException;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.io.Serializable;
import java.util.List;


//Handles the shopping lists
public class ShoppingListHandler extends ProductListHandler implements Serializable {
    private static ShoppingListPersistence shoppingListPersistence;
    private static PricePersistence pricePersistence;

    //constructor
    public ShoppingListHandler(boolean forProduction){
        super(forProduction);
        shoppingListPersistence = Services.getShoppingListPersistence(forProduction);
        pricePersistence = Services.getPricePersistence(forProduction);
    }

    public static List<ShoppingList> getAllShoppingLists(){
        return shoppingListPersistence.getExistingShoppingLists();
    }

    //create a new shopping list and add to overall shopping list array
    public static void createShoppingList(Store store) throws InvalidShoppingListException {
        if(store == null) {
            Log.e("ShoppingListHandler", "Null store passed when making shopping list!");
            return;
        }

        if(shoppingListPersistence.listWithStoreExists(store)) {
            throw new InvalidShoppingListException("Shopping list with store already exists!");
        }

        ShoppingList newList = new ShoppingList(store);
        createList(newList);
    }

    //Gives total price of the ShoppingList
    public static double getCartTotal(ShoppingList shoppingList){
        if(shoppingList == null || !listExists(shoppingList))
        {
            Log.e("ShoppingListHandler", "Invalid Shopping List passed when calculating cart total!");
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
    public static double getAllShoppingListTotal() {
        double total = 0;
        List<ShoppingList> shoppingLists = shoppingListPersistence.getExistingShoppingLists();

        for (ShoppingList shoppingList : shoppingLists){
            total += getCartTotal(shoppingList);
        }

        return total;
    }
}
