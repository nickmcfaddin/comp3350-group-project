package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String userName;
    private String userID;

    public User(String userName){
        this.userName = userName;
        this.userID = UUID.randomUUID().toString();
    }

    public User(String userName, String userID){
        this.userName = userName;
        this.userID = userID;
    }

    public String getUserID(){return userID;}

    public void setUserName(String newName){this.userName = newName;}

    public String getUserName(){return userName;}

    @NonNull
    @Override
    public String toString() {return userName;}

}
