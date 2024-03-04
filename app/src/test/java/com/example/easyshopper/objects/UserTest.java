package com.example.easyshopper.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    //Setup variables
    User user;

    @Before
    public void setup() {
        System.out.println("Starting test for User.class");

        user = new User("Jack");
    }

    @Test
    public void testGetUserName() {
        assertNotNull(user);
        assertEquals("Jack", user.getUserName());
    }

    @Test
    public void testSetUserName() {
        user.setUserName("John");
        assertEquals("John", user.getUserName());
    }
}
