package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.ShoppingListPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListPersistenceHSQLDB implements ShoppingListPersistence, Serializable {
    private List<ShoppingList> shoppingLists;

    private ListPersistenceHSQLDB listPersistenceHSQLDB;

    public ShoppingListPersistenceHSQLDB(String dbPath) {
        this.listPersistenceHSQLDB = new ListPersistenceHSQLDB(dbPath);

        loadShoppingLists();
    }

    private void loadShoppingLists() {
        shoppingLists = listPersistenceHSQLDB.getExistingShoppingLists();
    }

    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return shoppingLists;
    }

    @Override
    public void updateShoppingList(ShoppingList shoppingList) {
        listPersistenceHSQLDB.updateList(shoppingList);
        loadShoppingLists();
    }

    @Override
    public void addShoppingList(ShoppingList shoppingList) {
        listPersistenceHSQLDB.addList(shoppingList);
        loadShoppingLists();
    }

    @Override
    public void deleteShoppingList(ShoppingList shoppingList) {
        listPersistenceHSQLDB.deleteList(shoppingList);
        loadShoppingLists();
    }

    @Override
    public boolean shoppingListExists(ShoppingList queryList) {
        return listPersistenceHSQLDB.listExists(queryList);
    }

    @Override
    public boolean listWithStoreExists(Store queryStore) {
        if (queryStore == null) {
            return false;
        }

        for(ShoppingList list : shoppingLists) {
            if(list.getStore().getStoreID() == queryStore.getStoreID()) {
                return true;
            }
        }

        return false;
    }
}
