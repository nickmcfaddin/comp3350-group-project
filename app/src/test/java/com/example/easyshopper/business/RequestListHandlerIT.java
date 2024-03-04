package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

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
    private Product testProduct;
    private Product testProduct2;
    private Product testProduct3;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for RequestListHandler");
        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;
        requestHandler = new RequestListHandler(forProduction);
        testUser = new User("George", "1");

        testProduct = new Product(4, "Peanut Butter", 25.1, 20.9, 50.3, 180);
        testProduct2 = new Product(5, "Bread", 9, 49, 3, 7);
        testProduct3 = new Product(6, "Egg", 6, 0.6, 4.8, 30);
    }

    @Test
    public void testGetAllRequestLists(){
        assertEquals(0, requestHandler.getAllRequestLists().size());
    }

    @Test
    public void testCreateRequestList(){
        RequestListHandler.createRequestList(testUser);
        assertEquals(1, requestHandler.getAllRequestLists().size());
    }

    @Test
    public void testAddItemToRequestList(){
        assertEquals(0, requestHandler.getAllRequestLists().get(0).getCart().size());
        requestHandler.addItemToList(testProduct, requestHandler.getAllRequestLists().get(0));
        requestHandler.addItemToList(testProduct2, requestHandler.getAllRequestLists().get(0));
        requestHandler.addItemToList(testProduct3, requestHandler.getAllRequestLists().get(0));
        assertEquals(3, requestHandler.getAllRequestLists().get(0).getCart().size());
    }

    @Test
    public void testRemoveItemFromRequestList(){
        requestHandler.removeProduct(testProduct, requestHandler.getAllRequestLists().get(0));
        assertEquals(2, requestHandler.getAllRequestLists().get(0).getCart().size());
    }

    @Test
    public void testClearList(){
        requestHandler.clearList(requestHandler.getAllRequestLists().get(0));
        assertEquals(0, requestHandler.getAllRequestLists().get(0).getCart().size());
    }

    @Test
    public void testDeleteList(){
        requestHandler.deleteList(requestHandler.getAllRequestLists().get(0));
        assertEquals(0, requestHandler.getAllRequestLists().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
    }
}

