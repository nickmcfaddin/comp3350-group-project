package com.example.easyshopper.persistence.hsqldb;

import androidx.annotation.NonNull;

import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.RequestListPersistence;

import java.io.Serializable;
import java.util.List;

public class RequestListPersistenceHSQLDB implements RequestListPersistence, Serializable {

    private List<RequestList> requestLists;

    private ListPersistenceHSQLDB listPersistenceHSQLDB;

    public RequestListPersistenceHSQLDB(String dbPath) {
        this.listPersistenceHSQLDB = new ListPersistenceHSQLDB(dbPath);

        loadRequestLists();
    }

    private void loadRequestLists() {
        requestLists = listPersistenceHSQLDB.getExistingRequestLists();
    }

    @Override
    public List<RequestList> getExistingRequestLists() {
        return requestLists;
    }

    @Override
    public void updateRequestList(RequestList newRequestList) {
        listPersistenceHSQLDB.updateList(newRequestList);
        loadRequestLists();
    }

    @Override
    public void addRequestList(RequestList requestList) {
        listPersistenceHSQLDB.addList(requestList);
        loadRequestLists();
    }

    @Override
    public void deleteRequestList(RequestList requestList) {
        listPersistenceHSQLDB.deleteList(requestList);
        loadRequestLists();
    }

    @Override
    public boolean requestListExists(RequestList queryList) {
        return listPersistenceHSQLDB.listExists(queryList);
    }

    @Override
    public boolean listWithUserExists(User queryUser) {
        if (queryUser == null) {
            return false;
        }

        for(RequestList list : requestLists) {
            if(list.getUser().getUserID().equals(queryUser.getUserID())) {
                return true;
            }
        }

        return false;
    }

}
