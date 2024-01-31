package com.example.easyshopper.objects;

import java.util.ArrayList;

public class ShoppingList {
    private int shoppingListID;
    private ArrayList<Product> items;
    private Store store;
    private int storeID;
    private double totalAmount;

    public ShoppingList(int shoppingListID, Store store){
        this.shoppingListID = shoppingListID;
        this.store = store;
        this.storeID = store.getStoreID();
        this.items = new ArrayList<>();
        this.totalAmount = 0;
    }

    // GETTERS
    public Store getStore(){
        return store;
    }

    public int getShoppingListID() {
        return shoppingListID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isEmpty(){
        return items.size() == 0;
    }

    public ArrayList<Product> getItemList(){
        return items;
    }

    // trying to add item object into list, if the object from different store return false
    public boolean searchProduct(Product item) {
        for (Product i : items){
            if (item.getProductName().equals(i.getProductName()) && item.getStore().equals(this.store.getStoreName())){
                return true;
            }
        }
        return false;
    }

    // use for search item by name in the list
    public Product searchItem(String item) {
        for(Product i : items){
            if(i.getProductName().equals(item))
                return i;
        }
        return null;
    }

    public void addItem(Product item){
        if(!searchProduct(item)) {
            items.add(item);
            totalAmount += item.getPrice();
        }
    }
    
    public void deleteItem(Product item){
        if(searchProduct(item)) {
            items.remove(item);
            totalAmount -= item.getPrice();
        }
    }

    //
    public void calcTotalAmount(){
        double total = 0;

        for (Product i : items){
            total += i.getPrice();
        }

        totalAmount = total;
    }
}
