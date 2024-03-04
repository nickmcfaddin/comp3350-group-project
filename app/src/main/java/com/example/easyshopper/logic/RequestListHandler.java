package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.RequestListPersistence;
import com.example.easyshopper.persistence.ShoppingListPersistence;


import java.io.Serializable;
import java.util.List;


public class RequestListHandler implements Serializable {
    private static RequestListPersistence requestListPersistence;

    //constructor
    public RequestListHandler(boolean forProduction) {
        requestListPersistence = Services.getRequestListPersistence(forProduction);
    }

    public static List<RequestList> getAllRequestLists() {
        return requestListPersistence.getExistingRequestLists();
    }

    public static void createRequestList(User user){
        if(!requestListPersistence.listWithUserExists(user)){
            RequestList newRequestList = new RequestList(user);
            requestListPersistence.addRequestList(newRequestList);
        }
    }

    //add item into the given shopping list
    public static void addItemToList(Product newProduct, RequestList requestList){
        if(!requestListPersistence.requestListExists(requestList) ||
                newProduct == null || requestList == null) {

            return;
        }

        requestList.addProductToCart(newProduct);
        requestListPersistence.updateRequestList(requestList);
    }

    //remove an item from a shopping list
    public static void removeProduct(Product product, RequestList requestList){
        if(!requestListPersistence.requestListExists(requestList)||
                product == null || requestList == null) {
            return;
        }

        requestList.removeProductFromCart(product);
        requestListPersistence.updateRequestList(requestList);
    }

    public static void clearList(RequestList requestList) {
        if(requestList != null) {
            requestList.getCart().clear();
            requestListPersistence.updateRequestList(requestList);
        }
    }

    public static void deleteList(RequestList requestList) {
        if(requestList != null) {
            requestListPersistence.deleteRequestList(requestList);
        }
    }

}
