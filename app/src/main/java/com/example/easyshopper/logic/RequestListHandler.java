package com.example.easyshopper.logic;

import android.util.Log;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.exceptions.InvalidRequestListException;
import com.example.easyshopper.logic.exceptions.LogicException;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.RequestListPersistence;

import java.io.Serializable;
import java.util.List;

import javax.security.auth.login.LoginException;

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

    public static void createRequestList(User user) throws InvalidRequestListException {
        if(user == null) {
            Log.e("RequestListHandler", "Null user was passed when making request list!");
            return;
        }

        if(requestListPersistence.listWithUserExists(user)) {
            throw new InvalidRequestListException("User with list already exists!");
        }

        RequestList newRequestList = new RequestList(user);
        createList(newRequestList);
    }
}
