package com.example.easyshopper.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
    private ArrayList<User> users;

    public UserList(){
        users = new ArrayList<User>();
    }

    public void addUser(User newUser){
        if(!checkUserInList(newUser))
            users.add(newUser);
    }

    public void removeUser(User user){
        if(checkUserInList(user))
            users.remove(user);
    }

    public Boolean checkUserInList(User checkUser){
        for(User i : users){
            if(i.equals(checkUser)){
                return true;
            }
        }
        return false;
    }

    public User searchUserById(int ID){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserID() == ID) {
                return users.get(i);
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
