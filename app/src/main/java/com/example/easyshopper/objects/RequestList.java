package com.example.easyshopper.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestList implements Serializable {
    private ArrayList<Product> requestProducts;
    private ArrayList<User> requestUsers;
    private ArrayList<Boolean> requestAcception;

    public RequestList(){
        requestProducts = new ArrayList<Product>();
        requestUsers = new ArrayList<User>();
        requestAcception = new ArrayList<Boolean>();
    }

    public void addRequest(Product newProduct, User user){
        if(checkProductInList(newProduct)){
            System.out.println("request product already in list");
            return;
        }
        requestProducts.add(newProduct);
        requestUsers.add(user);
        requestAcception.add(false);
    }

    public void removeRequestByProduct(Product product){
        if(checkProductInList(product)){
            int productIndex = requestProducts.indexOf(product);
            requestProducts.remove(productIndex);
            requestAcception.remove(productIndex);
            requestUsers.remove(productIndex);
        }
    }

    public void removeRequestByName( User user){
        for(User i : requestUsers){
            if(i.equals(user)){
                int index = requestUsers.indexOf(i);
                requestUsers.remove(index);
                requestAcception.remove(index);
                requestProducts.remove(index);
            }
        }
    }

    public void removeAcceptedProduct(){
        for(int i = 0; i < requestAcception.size(); i++){
            if(requestAcception.get(i)){
                requestUsers.remove(i);
                requestAcception.remove(i);
                requestProducts.remove(i);
                i--;
            }
        }
    }
    public void removeRequestByOrder(){
        if(requestProducts.size() > 0){
            requestUsers.remove(0);
            requestAcception.remove(0);
            requestProducts.remove(0);
        }
    }

    public ArrayList<User> getRequestUsers(){
        return requestUsers;
    }

    public ArrayList<Boolean> getRequestAcception() {
        return requestAcception;
    }

    public ArrayList<Product> getRequestProducts() {
        return requestProducts;
    }

    public Boolean checkProductInList(Product newProduct){
        for(Product i: requestProducts){
            if(i.getProductID() == newProduct.getProductID()) {
                return true;
            }
        }
        return false;
    }
}
