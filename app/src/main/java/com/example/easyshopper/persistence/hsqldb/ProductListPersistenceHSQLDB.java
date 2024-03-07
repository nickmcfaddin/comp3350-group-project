package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.ProductListVisitor;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.ProductListPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductListPersistenceHSQLDB implements Serializable, ProductListVisitor, ProductListPersistence {
    private final String dbPath;
    private List<ShoppingList> shoppingLists;
    private List<RequestList> requestLists;
    private List<ProductList> productLists;

    private StorePersistenceHSQLDB storePersistenceHSQLDB;
    private ProductPersistenceHSQLDB productPersistenceHSQLDB;
    private UserPersistenceHSQLDB userPersistenceHSQLDB;

    public ProductListPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.storePersistenceHSQLDB = new StorePersistenceHSQLDB(dbPath);
        this.productPersistenceHSQLDB = new ProductPersistenceHSQLDB(dbPath);
        this.userPersistenceHSQLDB = new UserPersistenceHSQLDB(dbPath);

        loadProductLists();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private void loadProductLists() {
        //clear existing lists
        this.shoppingLists = new ArrayList<>();
        this.requestLists = new ArrayList<>();
        this.productLists = new ArrayList<>();

        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTLIST");

            while (resultSet.next()) {
                // Retrieve ListID, StoreID, and UserID from the result set
                String listID = resultSet.getString("ListID");
                int storeID = resultSet.getInt("StoreID");
                String userID = resultSet.getString("UserID");

                //prepare list
                ProductList productList;
                List<Product> cart = loadCart(listID, connection);

                // Determine if it's a shopping list or a user list
                // based on whether userID is null or not
                if (!resultSet.wasNull()) {
                    User user = userPersistenceHSQLDB.getUserById(userID);

                    productList = new RequestList(listID, cart, user);
                    requestLists.add((RequestList) productList);
                } else {
                    Store store = storePersistenceHSQLDB.getStoreById(storeID);

                    productList = new ShoppingList(listID, cart, store);
                    shoppingLists.add((ShoppingList) productList);
                }

                productLists.add(productList);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private List<Product> loadCart(String listID, Connection connection) {
        List<Product> cart = new ArrayList<>();

        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM CONTAINS WHERE CONTAINS.ListID = ?");
            statement.setString(1, listID);

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                cart.add(productPersistenceHSQLDB.getProductById(productID));
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return cart;
    }

    public List<ShoppingList> getExistingShoppingLists() {
        return shoppingLists;
    }

    public List<RequestList> getExistingRequestLists() {
        return requestLists;
    }

    public void updateList(ProductList productList) {
        if(productList == null) {
            return;
        }

        try (Connection connection = connect()) {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCTLIST set UserID = ?, StoreID = ? WHERE ListID = ?");
            statement.setString(3, productList.getListID());

            if(productList instanceof ShoppingList) {
                statement.setString(1, null);
                statement.setInt(2,((ShoppingList) productList).getStore().getStoreID());
            } else if (productList instanceof RequestList){
                statement.setString(1,((RequestList) productList).getUser().getUserID());
                statement.setNull(2, java.sql.Types.INTEGER); // Set StoreID to NULL
            }

            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM CONTAINS WHERE ListID = ?");
            statement.setString(1,productList.getListID());
            statement.executeUpdate();

            for(Product product : productList.getCart()) {
                statement = connection.prepareStatement("INSERT INTO CONTAINS VALUES(?,?,0)");
                statement.setString(1, productList.getListID());
                statement.setInt(2, product.getProductID());

                statement.executeUpdate();
            }

            connection.commit();
            statement.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    public void addList(ProductList productList) {
        if(productList == null) {
            return;
        }

        try (Connection connection = connect()) {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCTLIST VALUES (?,?,?)");
            statement.setString(1, productList.getListID());

            if(productList instanceof ShoppingList) {
                statement.setString(3, null);
                statement.setInt(2,((ShoppingList) productList).getStore().getStoreID());
            } else if (productList instanceof RequestList){
                statement.setString(3,((RequestList) productList).getUser().getUserID());
                statement.setNull(2, java.sql.Types.INTEGER); // Set StoreID to NULL
            }

            statement.executeUpdate();

            for(Product product : productList.getCart()) {
                statement = connection.prepareStatement("INSERT INTO CONTAINS VALUES(?,?,0)");
                statement.setString(1, productList.getListID());
                statement.setInt(2, product.getProductID());

                statement.executeUpdate();
            }

            statement.close();
            connection.commit();

            productLists.add(productList);
            productList.add(this);

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    public void deleteList(ProductList productList) {
        if(productList == null) {
            return;
        }

        try (Connection connection = connect()) {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("DELETE FROM CONTAINS WHERE ListID = ?");
            statement.setString(1,productList.getListID());
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM PRODUCTLIST WHERE ListID = ?");
            statement.setString(1, productList.getListID());
            statement.executeUpdate();

            connection.commit();
            statement.close();

            productLists.remove(productList);
            productList.delete(this);
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    public boolean listExists(ProductList queryList) {
        if(queryList == null) {
            return false;
        }

        for(ProductList productList : productLists) {
            if(productList.getListID().equals(queryList.getListID())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void add(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
    }

    @Override
    public void add(RequestList requestList) {
        requestLists.add(requestList);
    }

    @Override
    public void delete(ShoppingList shoppingList) {
        shoppingLists.remove(shoppingList);
    }

    @Override
    public void delete(RequestList requestList) {
        requestLists.remove(requestList);
    }
}
