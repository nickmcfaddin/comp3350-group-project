package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.logic.UserHandler;

import org.junit.Before;
import org.junit.Test;

public class UserHandlerTest {
    private UserHandler userHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for UserHandler");
        boolean forProduction = false;

        userHandler = new UserHandler(forProduction);
    }

    //Weird case, currently fails every second time as the db doesn't reset to not include the test user that is created
    @Test
    public void testGetAll(){
        assertEquals(5, userHandler.getExistingUsers());
    }

    @Test
    public void testCreateUser(){
        //Tests the valid name function within this
        userHandler.createUser("InV@l1d N@m3");
        assertEquals(5, userHandler.getExistingUsers().size());

        userHandler.createUser("Fred");
        assertEquals(6, userHandler.getExistingUsers().size());
    }
}