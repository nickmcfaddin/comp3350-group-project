package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.logic.exceptions.NoProductFoundException;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.ProductPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductPersistenceHSQLDB implements ProductPersistence, Serializable {
    private final String dbPath;
    private List<Product> products;

    public ProductPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.products = new ArrayList<>();
        loadProducts();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Product fromResultSet(final ResultSet rs) throws SQLException {
        try
        {
            int productID = rs.getInt(ColumnNames.PRODUCT_ID);
            String name = rs.getString(ColumnNames.NAME);
            double protein = rs.getBigDecimal(ColumnNames.PROTEIN).doubleValue();
            double carbs = rs.getBigDecimal(ColumnNames.CARBS).doubleValue();
            double fat = rs.getBigDecimal(ColumnNames.FAT).doubleValue();
            int lifetimeDays = rs.getInt(ColumnNames.LIFETIME_DAYS);

            return new Product(productID, name, fat, carbs, protein, lifetimeDays);

        } catch (SQLException e) {
            Log.e("Connect SQL", "Could not parse result set: " + rs);
            e.printStackTrace();
        }

        return null;
    }

    private void loadProducts() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS");

            while (resultSet.next()) {
                final Product product = fromResultSet(resultSet);

                if(product != null) {
                    this.products.add(product);
                }
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getExistingProducts() {
        return products;
    }

    @Override
    public Product getProductById(int productID) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == productID) {
                return products.get(i);
            }
        }

        throw new NoProductFoundException("Product with id: " + productID + " not found!");
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        List<Product> newList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().toLowerCase().contains(productName.toLowerCase())) {
                newList.add(products.get(i));
            }
        }

        return newList;
    }
}
