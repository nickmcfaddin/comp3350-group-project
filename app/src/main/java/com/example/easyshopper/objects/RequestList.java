package com.example.easyshopper.objects;

import java.util.List;

public class RequestList extends ProductList {
    private User requestUser;

    public RequestList(User user){
        super();
        this.requestUser = user;
    }

    public RequestList(String listID, List<Product> cart, User user){
        super(listID, cart);
        this.requestUser = user;
    }

    public User getUser(){return requestUser;}
}
