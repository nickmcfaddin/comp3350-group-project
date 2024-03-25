package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeProductPersistenceHSQLDB implements HomeProductPersistence, Serializable {
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
                int productID = resultSet.getInt(ColumnNames.PRODUCT_ID);
                int stockQuantity = resultSet.getInt(ColumnNames.STOCK_QUANTITY);
                int desiredQuantity = resultSet.getInt(ColumnNames.DESIRED_QUANTITY);

                Product product = productPersistenceHSQLDB.getProductById(productID);

                if(product == null) {
                    continue;
                }

                List<String> expiryDates = loadExpiryDates(productID, connection);

                HomeProduct homeProduct = new HomeProduct(product, stockQuantity, desiredQuantity,expiryDates);
                homeProducts.add(homeProduct);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private List<String> loadExpiryDates(int productID, Connection connection) throws SQLException {
        List<String> expiryDates = new ArrayList<>();

        final PreparedStatement statement = connection.prepareStatement("SELECT * FROM EXPIRYDATES WHERE EXPIRYDATES.ProductID = ?");
        statement.setInt(1, productID);

        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            expiryDates.add(resultSet.getString(ColumnNames.EXPIRY_DATE));
        }

        return expiryDates;
    }

    @Override
    public List<HomeProduct> getStockProduct() {
        List<HomeProduct> stockProduct = new ArrayList<>();

        for (int i=0; i<homeProducts.size(); i++){
            HomeProduct curHomeProduct = homeProducts.get(i);

            if (curHomeProduct.getHomeProductDesiredQuantity() != 0 || curHomeProduct.getHomeProductStockQuantity() != 0) {
                stockProduct.add(homeProducts.get(i));
            }
        }

        return stockProduct;
    }

    public List<HomeProduct> getStockProductSorted() {
        // sort product by desired/have quantity decreasing order
        // if have = 0 and desired = 1, it will be at the top
        List<HomeProduct> stockProductWithHave0 = new ArrayList<>();
        List<HomeProduct> stockProductWithoutHave0 = new ArrayList<>();

        for (int i=0; i<homeProducts.size(); i++){
            HomeProduct curHomeProduct = homeProducts.get(i);

            if (curHomeProduct.getHomeProductDesiredQuantity() != 0 || curHomeProduct.getHomeProductStockQuantity() != 0) {
                if (curHomeProduct.getHomeProductStockQuantity() == 0){
                    stockProductWithHave0.add(curHomeProduct);
                }
                else{
                    stockProductWithoutHave0.add(curHomeProduct);
                }
            }
        }

        stockProductWithoutHave0.sort(new Comparator<HomeProduct>() {
            public int compare(HomeProduct product1, HomeProduct product2) {
                // Calculate the ratio of have/desired for each product
                double ratio1 = product1.getHomeProductDesiredQuantity() / (double) product1.getHomeProductStockQuantity();
                double ratio2 = product2.getHomeProductDesiredQuantity() / (double) product2.getHomeProductStockQuantity();

                // Compare the ratios in decreasing order
                return Double.compare(ratio2, ratio1);
            }
        });

        stockProductWithHave0.addAll(stockProductWithoutHave0);

        return stockProductWithHave0;
    }

    public List<HomeProduct> getHiddenProduct(){
        List<HomeProduct> hiddenProduct = new ArrayList<>();

        for (int i=0; i<homeProducts.size(); i++){
            if (homeProducts.get(i).getHomeProductDesiredQuantity() == 0 && homeProducts.get(i).getHomeProductStockQuantity() == 0) {
                hiddenProduct.add(homeProducts.get(i));
            }
        }

        return hiddenProduct;
    }

    private void updateHomeProduct(HomeProduct homeProduct) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE HOMEPRODUCTS set StockQuantity = ?, DesiredQuantity = ? WHERE ProductID = ?");
            statement.setInt(1, homeProduct.getHomeProductStockQuantity());
            statement.setInt(2, homeProduct.getHomeProductDesiredQuantity());
            statement.setInt(3, homeProduct.getProductID());

            statement.executeUpdate();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    public void incrementStockQuantity(HomeProduct homeProduct){
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            curHomeProduct.incrementStockQuantity();
            homeProducts.set(curIndex, curHomeProduct);

            updateHomeProduct(homeProduct);
        }
    }

    public void incrementDesiredQuantity(HomeProduct homeProduct){
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            curHomeProduct.incrementDesiredQuantity();
            homeProducts.set(curIndex, curHomeProduct);

            updateHomeProduct(homeProduct);
        }
    }

    public void decreaseStockQuantity(HomeProduct homeProduct){
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            curHomeProduct.decreaseStockQuantity();
            homeProducts.set(curIndex, curHomeProduct);

            updateHomeProduct(homeProduct);
        }
    }

    public void decreaseDesiredQuantity(HomeProduct homeProduct){
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            curHomeProduct.decreaseDesiredQuantity();
            homeProducts.set(curIndex, curHomeProduct);

            updateHomeProduct(homeProduct);
        }
    }

    public List<String> getHomeProductExpiryDate(HomeProduct homeProduct) {
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            return curHomeProduct.getHomeProductExpiryDates();
        }

        return null;
    }

    public List<String> getHomeProductSortedExpiryDateAscending(HomeProduct homeProduct) {
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            return curHomeProduct.getSortedExpiryDatesAscending();
        }

        return null;
    }

    public List<String> getHomeProductSortedExpiryDateDescending(HomeProduct homeProduct) {
        if (homeProducts.contains(homeProduct)){
            int curIndex = homeProducts.indexOf(homeProduct);
            HomeProduct curHomeProduct = homeProducts.get(curIndex);

            return curHomeProduct.getSortedExpiryDatesDescending();
        }

        return null;
    }

    public List<HomeProduct> getHomeProducts() {
        return homeProducts;
    }
}
