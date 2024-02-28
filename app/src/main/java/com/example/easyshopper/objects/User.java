package com.example.easyshopper.objects;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
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
