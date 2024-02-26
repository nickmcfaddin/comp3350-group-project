package com.example.easyshopper.persistence.hsqldb;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeInventoryPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class HomeInventoryPersistenceHSQLDB implements HomeInventoryPersistence {
    private String dbPath;

    public HomeInventoryPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<HomeProduct> getAllHomeProduct() {
        return null;
    }

    @Override
    public HomeProduct getHomeProductFromHomeInventory(String homeProductName) {
        return null;
    }

    @Override
    public List<HomeProduct> getStockProduct() {
        return null;
    }

    @Override
    public List<HomeProduct> getSortedStockProduct() {
        return null;
    }

    @Override
    public List<HomeProduct> getHiddenProduct() {
        return null;
    }

    @Override
    public void incrementStockQuantityBy1(HomeProduct homeProduct, String date) {

    }

    @Override
    public void decreaseStockQuantityBy1(HomeProduct homeProduct) {

    }

    @Override
    public void incrementDesiredQuantityBy1(HomeProduct homeProduct) {

    }

    @Override
    public void decreaseDesiredQuantityBy1(HomeProduct homeProduct) {

    }

    @Override
    public List<String> getHomeProductExpiryDates(HomeProduct homeProduct) {
        return null;
    }

    @Override
    public List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct) {
        return null;
    }

    @Override
    public List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct) {
        return null;
    }
}
