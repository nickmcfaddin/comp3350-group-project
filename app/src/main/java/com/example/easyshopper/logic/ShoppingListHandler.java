package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.util.List;


//Handles the shopping lists
public class ShoppingListHandler {
    private ShoppingListPersistence shoppingListPersistence = Services.getShoppingListPersistence();
    private PricePersistence pricePersistence = Services.getPricePersistence();

    //construct
    public ShoppingListHandler(){}

    public String getShoppingListByName(){
        return null;
    }

    public List<ShoppingList> getAllShoppingLists(){
        return shoppingListPersistence.getExistingShoppingLists();
    }

    //add item into the given shopping list
    public void addItemToList(Product newProduct, ShoppingList shoppingList){
        shoppingList.addProductToCart(newProduct);
        shoppingListPersistence.updateShoppingList(shoppingList);
    }

    //create a new shopping list and add to overall shopping list array
    //idk How shoppingListId work, does it will be gaven as parameter with this function?
    public void createShoppingList(Store store){
        ShoppingList newList = new ShoppingList(store);

        shoppingListPersistence.addShoppingList(newList);
    }

    //remove given shoppingList from overall shopping list array
    public void removeShoppingList(ShoppingList shoppingList){
        shoppingListPersistence.deleteShoppingList(shoppingList);
    }

    //remove an item from a shopping list
    public void removeProduct(Product product, ShoppingList shoppingList){
        shoppingList.removeProductFromCart(product);
        shoppingListPersistence.updateShoppingList(shoppingList);
    }


    //Gives total price of the ShoppingList
    public double getCartTotal(ShoppingList shoppingList){
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
