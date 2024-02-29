package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private int userID;

    public User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
    }

    public int getUserID(){return userID;}

    public void setUserName(String newName){this.userName = newName;}

    public String getUserName(){return userName;}

    @NonNull
    @Override
    public String toString() {return userName;}

}
