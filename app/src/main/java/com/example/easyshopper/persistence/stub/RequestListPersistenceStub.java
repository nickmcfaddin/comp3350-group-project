package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.ProductListPersistence;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.RequestListPersistence;
import com.example.easyshopper.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class RequestListPersistenceStub implements RequestListPersistence {
    private ProductListPersistence productListPersistenceStub;

    public RequestListPersistenceStub(ProductListPersistence productListPersistenceStub) {
        this.productListPersistenceStub = productListPersistenceStub;
    }

    //Returns all currently existing RequestList's
    @Override
    public List<RequestList> getExistingRequestLists() {
        return productListPersistenceStub.getExistingRequestLists();
    }

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
