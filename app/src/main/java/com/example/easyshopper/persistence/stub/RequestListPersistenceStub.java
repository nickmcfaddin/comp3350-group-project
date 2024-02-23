package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.UserList;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.RequestListPersistence;
import com.example.easyshopper.persistence.UserListPersistence;

import java.util.List;

public class RequestListPersistenceStub implements RequestListPersistence {

    private RequestList requestList;

    public RequestListPersistenceStub(){
        this.requestList = new RequestList();

        Services services = new Services();
        UserListPersistence userListPersistence = services.getUserListPersistence();
        ProductPersistence productPersistence = services.getProductPersistence();

        List<Product> existingProducts = productPersistence.getExistingProducts();
        UserList users = userListPersistence.getUserList();

        requestList.addRequest(existingProducts.get(1), users.searchUserById(1));
        requestList.addRequest(existingProducts.get(1), users.searchUserById(2));
        requestList.addRequest(existingProducts.get(2), users.searchUserById(2));
        requestList.addRequest(existingProducts.get(3), users.searchUserById(2));
        requestList.addRequest(existingProducts.get(4), users.searchUserById(4));
    }

    @Override
    public RequestList getRequestList() {
        return requestList;
    }
}
