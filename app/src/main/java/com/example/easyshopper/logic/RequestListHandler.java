package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.RequestListPersistence;

import java.io.Serializable;
import java.util.List;

public class RequestListHandler extends ProductListHandler implements Serializable {
    private static RequestListPersistence requestListPersistence;

    //constructor
    public RequestListHandler(boolean forProduction) {
        super(forProduction);
        requestListPersistence = Services.getRequestListPersistence(forProduction);
    }

    public static List<RequestList> getAllRequestLists() {
        return requestListPersistence.getExistingRequestLists();
    }

    public static void createRequestList(User user){
        if(!requestListPersistence.listWithUserExists(user)){
            RequestList newRequestList = new RequestList(user);
            createList(newRequestList);
        }
    }
}
