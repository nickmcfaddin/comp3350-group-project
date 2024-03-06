package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.logic.RequestListHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestListHandlerTest {
    private User testUser;

    @Before
    public void setup() {
        System.out.println("Starting test for RequestListHandler");
        boolean forProduction = false;

        RequestListHandler requestHandler = new RequestListHandler(forProduction);
        testUser = new User("George", "10");
    }

    @Test
    public void testGetAllRequestLists(){
        // stub database have 5 request list by default
        assertEquals(5, RequestListHandler.getAllRequestLists().size());
    }

    @Test
    public void testCreateRequestList(){
        RequestListHandler.createRequestList(testUser);
        assertEquals(6, RequestListHandler.getAllRequestLists().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");

        Services.clean();
    }

}
