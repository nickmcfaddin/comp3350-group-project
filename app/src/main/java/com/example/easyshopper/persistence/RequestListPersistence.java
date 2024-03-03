package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;

import java.util.List;

public interface RequestListPersistence {

    //Returns all currently existing RequestList's
    List<RequestList> getExistingRequestLists();

    //Updates the RequestList's information
    void updateRequestList(RequestList newRequestList);

    //Adds a RequestList to the overall RequestList array
    void addRequestList(RequestList requestList);

    //Delete a RequestList from the overall RequestList array
    void deleteRequestList(RequestList requestList);

    void clearRequestList(RequestList requestList);

    boolean requestListExists(RequestList queryList);

    boolean listWithUserExists(User queryUser);

}
