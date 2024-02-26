package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.UserList;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeProductPersistenceHSQLDB implements HomeProductPersistence {
    private String dbPath;
    private List<HomeProduct> homeProducts;

    private ProductPersistenceHSQLDB productPersistenceHSQLDB;

    public HomeProductPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.productPersistenceHSQLDB = new ProductPersistenceHSQLDB(dbPath);
        loadHomeProducts();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }


    private void loadHomeProducts() {
        homeProducts = new ArrayList<>();

        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM HOMEPRODUCTS");

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                int stockQuantity = resultSet.getInt("StockQuantity");
                int desiredQuantity = resultSet.getInt("DesiredQuantity");

                Product product = productPersistenceHSQLDB.getProductById(productID);
                List<String> expiryDates = loadExpiryDates(productID, connection);

                HomeProduct homeProduct = new HomeProduct(product, stockQuantity, desiredQuantity,expiryDates);
                homeProducts.add(homeProduct);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private List<String> loadExpiryDates(int productID, Connection connection) {
        List<String> expiryDates = new ArrayList<>();

        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM EXPIRYDATES WHERE CONTAINS.ProductID = ?");
            statement.setInt(1, productID);

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                expiryDates.add(resultSet.getString("ExpiryDate"));
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return expiryDates;
    }
    @Override
    public List<HomeProduct> getExistingHomeProducts() {
        return homeProducts;
    }

    @Override
    public HomeProduct getHomeProductById(int productID) {
        return null;
    }
}
