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
    private final String dbPath;
    private List<ShoppingList> shoppingLists;

    public ShoppingListPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.shoppingLists = new ArrayList<>();
        loadShoppingLists();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private ShoppingList fromResultSet(final ResultSet rs) throws SQLException {

        return null;
    }

    private void loadShoppingLists() {

    }

    @Override
    public List<ShoppingList> getExistingShoppingLists() {
        return null;
    }

    @Override
    public void updateShoppingList(ShoppingList shoppingList) {

    }

    @Override
    public void addShoppingList(ShoppingList shoppingList) {

    }

    @Override
    public void deleteShoppingList(ShoppingList shoppingList) {

    }

    @Override
    public boolean shoppingListExists(ShoppingList queryList) {
        return false;
    }

    @Override
    public boolean listWithStoreExists(Store queryStore) {
        return false;
    }
}
