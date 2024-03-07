package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.User;

import java.util.List;

public interface UserPersistence {
    User getUserByName(String name);

    User getUserById(String Id);

    List<User> getExistingUsers();

    User createUser(User user);
}
