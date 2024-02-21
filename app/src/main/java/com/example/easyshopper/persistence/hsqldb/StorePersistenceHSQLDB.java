package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.StorePersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StorePersistenceHSQLDB implements StorePersistence, Serializable {
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
        return stores;
    }

    @Override
    public Store getStoreById(int storeID) {
        for (int i=0; i<stores.size(); i++){
            if (stores.get(i).getStoreID() == storeID)
            {
                return stores.get(i);
            }
        }
        return null;
    }

    @Override
    public void addStore(Store store) {
        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO STORES VALUES(?, ?)");
            statement.setInt(1, store.getStoreID());
            statement.setString(2, store.getStoreName());

            statement.executeUpdate();
            statement.close();

            this.stores = new ArrayList<>();
            loadStores();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }
}
