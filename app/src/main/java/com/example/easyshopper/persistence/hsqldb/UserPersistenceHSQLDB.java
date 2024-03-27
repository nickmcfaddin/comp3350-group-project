package com.example.easyshopper.persistence.hsqldb;

import android.util.Log;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.UserPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        loadUsers();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private void loadUsers() {
        users = new ArrayList<>();

        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");

            while (resultSet.next()) {
                String userID = resultSet.getString(ColumnNames.USER_ID);
                String userName = resultSet.getString(ColumnNames.NAME);

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
        User user = null;
        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER WHERE UserID = ?");
            statement.setString(1, Id); // Set the parameter value

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userID = resultSet.getString(ColumnNames.USER_ID);
                String userName = resultSet.getString(ColumnNames.NAME);

                user = new User(userName, userID);
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getExistingUsers() {
        return users;
    }

    @Override
    public User createUser(User user) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USER VALUES(?,?)");
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getUserName());

            statement.executeUpdate();
            loadUsers();
            return user;
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return null;
    }
}
