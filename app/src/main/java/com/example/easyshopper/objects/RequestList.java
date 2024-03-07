package com.example.easyshopper.objects;

import androidx.annotation.NonNull;

import java.util.List;

public class RequestList extends ProductList {
    private User user;

    public RequestList(User user){
        super();
        this.user = user;
    }

    //Not included in testing
    public RequestList(String listID, List<Product> cart, User user){
        super(listID, cart);
        this.user = user;
    }

    public String getListName(){
        return user.getUserName();
    }

    public User getUser(){return user;}

    @NonNull
    @Override
    public String toString() {
        return user.getUserName();
    }

    public void add(ProductListVisitor visitor) {
        visitor.add(this);
    }

    public void delete(ProductListVisitor visitor){
        visitor.delete(this);
    }

}
