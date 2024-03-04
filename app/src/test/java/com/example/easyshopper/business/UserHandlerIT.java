package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.logic.UserHandler;
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
        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;
        userHandler = new UserHandler(forProduction);
    }

    //Weird case, currently fails every second time as the db doesn't reset to not include the test user that is created
    @Test
    public void testGetAll(){
        assertEquals(0, userHandler.getExistingUsers().size());
    }

    @Test
    public void testCreateUser(){
        //Tests the valid name function within this
        userHandler.createUser("InV@l1d N@m3");
        assertEquals(0, userHandler.getExistingUsers().size());

        userHandler.createUser("Fred");
        assertEquals(1, userHandler.getExistingUsers().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
    }
}