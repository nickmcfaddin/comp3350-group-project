package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.persistence.PricePersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PricePersistenceHSQLDB implements PricePersistence, Serializable {
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
        int storeID = rs.getInt(ColumnNames.STORE_ID);
        int productID = rs.getInt(ColumnNames.PRODUCT_ID);
        double price = rs.getBigDecimal(ColumnNames.PRICE).doubleValue();

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
        for (int i=0; i<prices.size(); i++){
            if (prices.get(i).getProductID() == product.getProductID() && prices.get(i).getStoreID() == store.getStoreID()){
                return prices.get(i).getPrice();
            }
        }

        return -1;
    }

    @Override
    public List<Price> getAllPricesForSameProduct(Product product) {
        List<Price> returnList = new ArrayList<>();

        for (int i=0; i<prices.size(); i++){
            if (prices.get(i).getProductID() == product.getProductID()){
                returnList.add(prices.get(i));
            }
        }

        return returnList;
    }
}
