package com.example.easyshopper.objects;
import java.util.ArrayList;

public class Product {
    String itemName;
    float price;
    ArrayList<String> catgories;
    String[] contains;
    Store store;

    public Product(String itemName, float price, ArrayList<String> catgories, String[] contains, Store store) {
        this.itemName = itemName;
        this.price = price;
        this.catgories = catgories;
        this.contains = contains;
        this.store = store;
    }

    public String getItemName() {
        return this.itemName;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    public ArrayList<String> getCatgories() {
        return catgories;
    }

    public boolean searchCatgory(String catgory) {
        for (String i : catgories) {
            if (i.equals(catgory))
                return true;
        }
        return false;
    }

    public void addCatgory(String catgory) {
        if (!searchCatgory(catgory))
            catgories.add(catgory);
    }

    public void deleteCatgory(String catgory) {
        if (searchCatgory(catgory))
            catgories.remove(catgory);
    }

    public String[] getContains() {
        return contains;
    }

    public boolean searchContain(String contain) {
        for (String i : contains) {
            if (i.equals(contain))
                return true;
        }
        return false;
    }

    //for same item in different stor, maybe should deal it in database
    public String getStore(){
        return this.store.getStoreName();
    }
}
