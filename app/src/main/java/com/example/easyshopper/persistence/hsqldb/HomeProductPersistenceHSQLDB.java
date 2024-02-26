package com.example.easyshopper.persistence.hsqldb;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeProductPersistenceHSQLDB implements HomeProductPersistence {
    private String dbPath;
    private List<HomeProduct> homeProduct;

    public HomeProductPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        loadHomeProducts();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private void loadHomeProducts() {
        homeProduct = new ArrayList<>();
    }

    @Override
    public List<HomeProduct> getExistingHomeProducts() {
        return homeProduct;
    }

    @Override
    public HomeProduct getHomeProductById(int productID) {
        return null;
    }
}
