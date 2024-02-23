package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.objects.UserList;
import com.example.easyshopper.persistence.UserListPersistence;
import com.example.easyshopper.persistence.UserPersistence;

import java.util.List;

public class UserListPersistenceStub implements UserListPersistence {

    private UserList userList;
    public UserListPersistenceStub(){
        this.userList = new UserList();

        Services services = new Services();
        UserPersistence userPersistence = services.getUserPersistence();

        List<User> existingUsers = userPersistence.getExistingUsers();

        for(User i : existingUsers){
            this.userList.addUser(i);
        }

    }
    @Override
    public UserList getUserList(){
        return this.userList;
    }


}
