package com.example.easyshopper.persistence.stub;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
<<<<<<< HEAD
import com.example.easyshopper.objects.User;
import com.example.easyshopper.persistence.ProductPersistence;
import com.example.easyshopper.persistence.RequestListPersistence;
import com.example.easyshopper.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class RequestListPersistenceStub implements RequestListPersistence {
    private List<RequestList> requestListArray;

    //Constructor
    public RequestListPersistenceStub(){
        this.requestListArray = new ArrayList<>();

        //Setup connections to UserPersistence and ProductPersistence stubs
        Services services = new Services();
        UserPersistence userPersistence = services.getUserPersistence(false);
        ProductPersistence productPersistence = services.getProductPersistence(false);

        List<User> existingUsers = userPersistence.getExistingUsers();
        List<Product> existingProducts = productPersistence.getExistingProducts();

        for (int i=0; i < existingUsers.size(); i++){
            requestListArray.add(new RequestList(existingUsers.get(i)));
        }

        for (int i=0; i<requestListArray.size(); i++){
            RequestList indexList = requestListArray.get(i);

            for (int j=0; j<existingProducts.size(); j++){
                indexList.addProductToCart(existingProducts.get(j));
            }

            requestListArray.set(i, indexList);
        }
    }

    //Returns all currently existing RequestList's
    @Override
    public List<RequestList> getExistingRequestLists() {
        return requestListArray;
    }

    //Updates the RequestList's information
    @Override
    public void updateRequestList(RequestList newRequestList) {
        int index = -1;

        for (int i = 0; i < requestListArray.size(); i++){
            if (requestListArray.get(i).getListID().equals(newRequestList.getListID())) {
                // productID will not repeat
                index = i;
            }
        }

        if (index != -1) {
            requestListArray.set(index, newRequestList);
        }
    }

    //Adds a RequestList to the overall RequestList array
    @Override
    public void addRequestList(RequestList requestList) {
        if (requestList != null) {
            requestListArray.add(requestList);
        }
    }

    //Delete a RequestList from the overall RequestList array by it's requestListID
    @Override
    public void deleteRequestList(RequestList requestList) {
        if (requestList != null) {
            requestListArray.remove(requestList);
        }
    }

    @Override
    public void clearRequestList(RequestList requestList){
        if(requestList != null) {
            requestList.getCart().clear();
            updateRequestList(requestList);
        }
    }

    public boolean requestListExists(RequestList queryList) {
        if (queryList == null) {
            return false;
        }

        for(RequestList list : requestListArray) {
            if(list.getListID().equals(queryList.getListID())) {
                return true;
            }
        }

        return false;
    }

    public boolean listWithUserExists(User queryUser) {
        if (queryUser == null) {
            return false;
        }

        for(RequestList list : requestListArray) {
            if(list.getUser().getUserID() == queryUser.getUserID()) {
                return true;
            }
        }

        return false;
=======
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
>>>>>>> c962722 (user profile classes, requestlist class and persistence about these)
    }
}
