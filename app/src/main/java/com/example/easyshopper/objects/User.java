package com.example.easyshopper.objects;

import java.util.UUID;

public class User {
    private String userID;

    public User() {
        this.userID = UUID.randomUUID().toString();
    }

    public User(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
