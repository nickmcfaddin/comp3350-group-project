package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.RequestListPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;


import java.io.Serializable;
import java.util.List;


public class RequestListHandler implements Serializable {

    private RequestListPersistence requestListPersistence;
    private ShoppingListPersistence shoppingListPersistence;

    //constructor
    public RequestListHandler(boolean forProduction) {
        requestListPersistence = Services.getRequestListPersistence(forProduction);
        shoppingListPersistence = Services.getShoppingListPersistence(forProduction);
    }



    public List<RequestList> getAllRequestLists() {
        return requestListPersistence.getExistingRequestLists();
    }

    public void addRequestToList(Product newProduct, RequestList requestList) {
        if(!requestListPersistence.requestListExists(requestList) ||
          newProduct == null || requestList == null) {
            System.out.println("Error no good!");
        }
        requestList.addProductToCart(newProduct);
        requestListPersistence.updateRequestList(requestList);
    }

    public void addRequestToList(User user, Product newProduct){
        List<RequestList> lists = getAllRequestLists();
        for(int i = 0; i < lists.size(); i++){
            if(lists.get(i).getUser().equals(user)){
                RequestList requestList = lists.get(i);
                addRequestToList(newProduct,requestList);
            } else {
                System.out.print("User Not Found!");
            }
        }
    }


    public void deleteRequest(Product delProduct, RequestList requestList) {
        if(!requestListPersistence.requestListExists(requestList) ||
                delProduct == null || requestList == null) {
            System.out.println("Error no good!");
        }
        requestList.removeProductFromCart(delProduct);
        requestListPersistence.updateRequestList(requestList);
    }

    public void DeleteRequestList(RequestList delRequestList) {
        if(requestListPersistence.requestListExists(delRequestList)){
            requestListPersistence.clearRequestList(delRequestList);
        }
    }


    public void addRequestToShoppingList(Product addProduct, RequestList requestList, ShoppingList shoppingList) {
        if(requestListPersistence.requestListExists(requestList) &&
                requestList.checkForProductInCart(addProduct)) {
            shoppingList.addProductToCart(addProduct);
            deleteRequest(addProduct, requestList);
        } else {
            System.out.println(addProduct.getProductName()+" was not found!");
        }
    }


}
