package com.example.easyshopper.objects;

import java.util.List;

public class RequestList extends ProductList {
    private User user;

    public RequestList(User user){
        super();
        this.user = user;
    }

    public RequestList(String listID, List<Product> cart, User user){
        super(listID, cart);
        this.user = user;
    }

    public String getUserName(){
        return user.getUserName();
    }

    public User getUser(){return user;}
}
