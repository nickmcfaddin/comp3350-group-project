package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.exceptions.InvalidUserException;
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

    public static User createUser(String name) throws InvalidUserException {
        if(validName(name)) {
            User newUser = new User(name);
            return userPersistence.createUser(newUser);
        }

        return null;
    }

    public static boolean validName(String name) throws InvalidUserException {
        // Regular expression pattern to match valid usernames
        String pattern = "^[a-zA-Z]+$";

        // Check if the name matches the pattern
        if(!Pattern.matches(pattern,name)) {
            throw new InvalidUserException("Usernames must contain only letters.");
        }

        return true;
    }
}
