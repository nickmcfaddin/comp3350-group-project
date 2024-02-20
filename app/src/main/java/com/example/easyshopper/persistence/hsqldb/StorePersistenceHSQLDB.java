package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StorePersistenceHSQLDB implements StorePersistence {
    private final String dbPath;
    private List<Store> stores;

    public StorePersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.stores = new ArrayList<>();
        loadStores();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Store fromResultSet(final ResultSet rs) throws SQLException {
        int storeID = rs.getInt("StoreID");
        String name = rs.getString("Name");

        return new Store(storeID,name);
    }

    private void loadStores() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM STORES");

            while (resultSet.next()) {
                final Store store = fromResultSet(resultSet);
                this.stores.add(store);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public List<Store> getExistingStores() {
        return null;
    }

    @Override
    public Store getStoreById(int storeID) {
        return null;
    }

    @Override
    public void addStore(Store store) {

    }
}
