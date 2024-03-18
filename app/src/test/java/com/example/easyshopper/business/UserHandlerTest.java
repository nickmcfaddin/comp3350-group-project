package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.logic.exceptions.InvalidUserException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserHandlerTest {
    @Before
    public void setup() {
        System.out.println("Starting test for UserHandler");

        boolean forProduction = false;
        UserHandler userHandler = new UserHandler(forProduction);
    }

    @Test
    public void testGetAll(){
        assertEquals(5, UserHandler.getExistingUsers().size());
    }

    @Test(expected = InvalidUserException.class)
    public void testCreateUser(){
        //Tests the valid name function within this
        UserHandler.createUser("InV@l1d N@m3");
        assertEquals(5, UserHandler.getExistingUsers().size());

        UserHandler.createUser("Fred");
        assertEquals(6, UserHandler.getExistingUsers().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");

        Services.clean();
    }
}