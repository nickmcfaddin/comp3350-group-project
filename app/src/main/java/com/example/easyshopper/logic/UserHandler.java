package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.UserPersistence;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

public class UserHandler implements Serializable {
    private static UserPersistence userPersistence;

    public UserHandler(boolean forProduction) {
        userPersistence = Services.getUserPersistence(forProduction);
    }

    public static List<User> getExistingUsers() {
        return userPersistence.getExistingUsers();
    }

    public static User createUser(String name) {
        if(!validName(name)) {
            return null;
        }

        User newUser = new User(name);
        return userPersistence.createUser(newUser);
    }

    public static boolean validName(String name){
        // Regular expression pattern to match valid usernames
        String pattern = "^[a-zA-Z0-9_-]+$";

        // Check if the name matches the pattern
        return Pattern.matches(pattern, name);
    }
}
