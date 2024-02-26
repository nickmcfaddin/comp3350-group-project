package com.example.easyshopper.objects;

import java.util.List;

public class UserList extends ProductList {
    private User user;

    public UserList(String listID, List<Product> cart, User user) {
        super(listID, cart);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
