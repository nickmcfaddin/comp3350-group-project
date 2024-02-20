package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PricePersistenceHSQLDB implements PricePersistence {
    private final String dbPath;
    private List<Price> prices;

    public PricePersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.prices = new ArrayList<>();
        loadPrices();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Price fromResultSet(final ResultSet rs) throws SQLException {
        int storeID = rs.getInt("StoreID");
        int productID = rs.getInt("ProductID");
        float price = rs.getBigDecimal("Price").floatValue();

        return new Price(storeID,productID,price);
    }

    private void loadPrices() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM PRICES");

            while (resultSet.next()) {
                final Price price = fromResultSet(resultSet);
                this.prices.add(price);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public double getPrice(Product product, Store store) {
        return 0;
    }

    @Override
    public List<Price> getAllPricesForSameProduct(Product product) {
        return null;
    }
}
