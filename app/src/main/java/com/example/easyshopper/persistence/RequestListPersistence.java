package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;

import java.util.List;

public interface RequestListPersistence {

    //Returns all currently existing RequestList's
    List<RequestList> getExistingRequestLists();

    boolean listWithUserExists(User queryUser);

}
