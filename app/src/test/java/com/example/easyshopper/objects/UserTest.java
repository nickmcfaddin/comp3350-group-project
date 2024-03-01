package com.example.easyshopper.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    @Before
    public void setup() {System.out.println("Starting test for User.class");
    }

    @Test
    public void testCreateAProduct() {
        System.out.println("\nStarting testCreateAUser");

        //Create Product to use for tests
        User user = new User("Jack");

        //Tests all GETTER methods
        assertNotNull(user);
        assertEquals("Jack", user.getUserName());

        user.setUserName("John");
        assertEquals("John", user.getUserName());

        System.out.println("Finished testCreateAUser");
    }
}
