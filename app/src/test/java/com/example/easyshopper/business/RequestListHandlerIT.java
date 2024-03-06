package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RequestListHandlerIT {
    private File tempDB;
    private RequestListHandler requestHandler;
    private User testUser;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for RequestListHandler");
        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;
        requestHandler = new RequestListHandler(forProduction);
        testUser = new User("George", "10");
    }

    @Test
    public void testGetAllRequestLists(){
        assertEquals(0, RequestListHandler.getAllRequestLists().size());
    }

    @Test
    public void testCreateRequestList(){
        RequestListHandler.createRequestList(testUser);
        System.out.println("abc "+RequestListHandler.getAllRequestLists());
        assertEquals(1, RequestListHandler.getAllRequestLists().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();

        Services.clean();
    }
}

