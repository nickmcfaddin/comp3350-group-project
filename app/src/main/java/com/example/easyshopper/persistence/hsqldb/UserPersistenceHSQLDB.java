package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.UserPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserPersistenceHSQLDB implements UserPersistence, Serializable {
    private List<User> users;
    private String dbPath;

    public UserPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        users = new ArrayList<>();
        loadUsers();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private void loadUsers() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");

            while (resultSet.next()) {
                String userID = resultSet.getString("UserID");
                String userName = resultSet.getString("Name");

                users.add(new User(userName, userID));
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByName(String name) {
        for(User i : users){
            if(i.getUserName().equals(name)){
                return i;
            }
        }
        return null;
    }

    @Override
    public User getUserById(String Id) {
        for(User i : users){
            if(i.getUserID().equals(Id)){
                return i;
            }
        }
        return null;
    }

    @Override
    public List<User> getExistingUsers() {
        return users;
    }
}
