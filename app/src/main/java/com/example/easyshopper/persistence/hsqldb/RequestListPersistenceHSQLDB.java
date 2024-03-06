package com.example.easyshopper.persistence.hsqldb;

import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.ProductListPersistence;
import com.example.easyshopper.persistence.RequestListPersistence;

import java.io.Serializable;
import java.util.List;

public class RequestListPersistenceHSQLDB implements RequestListPersistence, Serializable {
    private ProductListPersistence productListPersistenceHSQLDB;

    public RequestListPersistenceHSQLDB(ProductListPersistence productListPersistenceHSQLDB) {
        this.productListPersistenceHSQLDB = productListPersistenceHSQLDB;
    }

    @Override
    public List<RequestList> getExistingRequestLists() {
        return productListPersistenceHSQLDB.getExistingRequestLists();
    }

    @Override
    public boolean listWithUserExists(User queryUser) {
        if (queryUser == null) {
            return false;
        }

        for(RequestList list : getExistingRequestLists()) {
            if(list.getUser().getUserID().equals(queryUser.getUserID())) {
                return true;
            }
        }

        return false;
    }

}
