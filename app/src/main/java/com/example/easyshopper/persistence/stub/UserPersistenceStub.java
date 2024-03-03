package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPersistenceStub implements UserPersistence{

    private List<User> userList;

    public UserPersistenceStub(){
        this.userList = new ArrayList<>();

        userList.add(new User("Jack"));
        userList.add(new User("Child1"));
        userList.add(new User("Child2"));
        userList.add(new User("Child3"));
        userList.add(new User("Child4"));
    }

    @Override
    public User getUserByName(String name) {
        for(User i : userList){
            if(i.getUserName().equals(name)){
                return i;
            }
        }
        return null;
    }

    @Override
    public User getUserById(String Id) {
        for(User i : userList){
            if(i.getUserID().equals(Id)){
                return i;
            }
        }
        return null;
    }

    @Override
    public List<User> getExistingUsers() {
        return Collections.unmodifiableList(userList);
    }
}
