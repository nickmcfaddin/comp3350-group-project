package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.logic.exceptions.InvalidUserException;
import com.example.easyshopper.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UserHandlerIT {
    private File tempDB;
    private UserHandler userHandler;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for UserHandler");
        Services.clean();
        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;
        userHandler = new UserHandler(forProduction);
    }

    //Weird case, currently fails every second time as the db doesn't reset to not include the test user that is created
    @Test
    public void testGetAll(){
        assertEquals(0, UserHandler.getExistingUsers().size());
    }

    @Test(expected = InvalidUserException.class)
    public void testCreateUser(){
        //Tests the valid name function within this
        UserHandler.createUser("InV@l1d N@m3");
        assertEquals(0, UserHandler.getExistingUsers().size());

        UserHandler.createUser("Fred");
        assertEquals(1, UserHandler.getExistingUsers().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }
}