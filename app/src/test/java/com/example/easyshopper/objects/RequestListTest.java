package com.example.easyshopper.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RequestListTest {

    //Setup variables
    User user;
    RequestList requestList;
    Product product;
    ArrayList<Product> productArrayList;
    @Before
    public void setup() {
        System.out.println("Starting test for RequestList");

        //Create Store to use for tests
        user = new User("Jack", "1");

        //Create RequestList to use for tests
        requestList = new RequestList(user);

        //Create new Product to use for test
        product = new Product(1, "Eggs", 0.3, 5, 0.8, 1);

        //Add our new Product's to the ArrayList<Product> to test against
        productArrayList = new ArrayList<>();
        productArrayList.add(product);
    }

    @Test
    public void testGetUserName(){
        assertEquals("Jack", requestList.getListName());
    }

    @Test
    public void testGetUser(){
        assertEquals(user, requestList.getUser());
    }

    @Test
    public void testAddProductToCart(){
        requestList.addProductToCart(product);
        assertEquals(1, requestList.getCart().size());

        assertEquals(productArrayList, requestList.getCart());
        assertTrue(requestList.checkForProductInCart(product));
    }

    @Test
    public void testRemoveProductFromCart(){
        requestList.removeProductFromCart(product);
        assertEquals(0, requestList.getCart().size());
    }
}
