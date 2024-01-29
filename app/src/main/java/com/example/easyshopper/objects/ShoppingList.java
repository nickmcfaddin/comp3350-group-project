package com.example.easyshopper.objects;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Product> items;
    private Store store;
    private float totalAmount;
    public ShoppingList(Store store){
        this.items = new ArrayList<Product>();
        this.store = store;
        this.totalAmount = 0;
    }

    //trying to add item object into list, if the object from different store return false
    public boolean searchItem(Product item){
        for(Product i : items){
            if(item.getItemName().equals(i.getItemName()) && item.getStore().equals(this.store.getStoreName())){
                return true;
            }
        }
        return false;
    }

    //use for search item by name in the list
    public Product searchItem(String item) {
        for(Product i : items){
            if(i.getItemName().equals(item))
                return i;
        }
        return null;
    }

    public void addItem(Product item){
        if(!searchItem(item)) {
            items.add(item);
            totalAmount+=item.getPrice();
        }
    }
    
    public void deleteItem(Product item){
        if(searchItem(item)) {
            items.remove(item);
            totalAmount -= item.getPrice();
        }
    }


    public ArrayList<Product> getItemList(){
        return items;
    }

    public String getStore(){
        return this.store.getStoreName();
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public boolean isEmpty(){
        return items.size() == 0;
    }
}
