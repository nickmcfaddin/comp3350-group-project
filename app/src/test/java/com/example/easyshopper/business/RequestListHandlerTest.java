package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.logic.RequestListHandler;

import org.junit.Before;
import org.junit.Test;

public class RequestListHandlerTest {
    private RequestListHandler requestHandler;
    private User testUser;
    private Product testProduct;
    private Product testProduct2;
    private Product testProduct3;

    @Before
    public void setup() {
        System.out.println("Starting test for RequestListHandler");
        boolean forProduction = false;

        requestHandler = new RequestListHandler(forProduction);
        testUser = new User("George", "1");
        testProduct = new Product(100, "Eggs", 0.3, 5, 0.8, 1);
        testProduct2 = new Product(50, "Beans", 0.63, 3, 3.8, 3);
        testProduct3 = new Product(25, "Tomatoes", 0.4, 4, 4.8, 4);
    }

    @Test
    public void testGetAllRequestLists(){
        assertEquals(5, requestHandler.getAllRequestLists().size());
    }

    @Test
    public void testCreateRequestList(){
        RequestListHandler.createRequestList(testUser);
        assertEquals(6, requestHandler.getAllRequestLists().size());
    }

    @Test
    public void testAddItemToRequestList(){
        assertEquals(4, requestHandler.getAllRequestLists().get(0).getCart().size());
        requestHandler.addProductToCart(testProduct, requestHandler.getAllRequestLists().get(0));
        requestHandler.addProductToCart(testProduct2, requestHandler.getAllRequestLists().get(0));
        requestHandler.addProductToCart(testProduct3, requestHandler.getAllRequestLists().get(0));
        assertEquals(7, requestHandler.getAllRequestLists().get(0).getCart().size());
    }

    //Something wrong here
    @Test
    public void testRemoveItemFromRequestList(){
        assertEquals(4, requestHandler.getAllRequestLists().get(0).getCart().size());
        requestHandler.removeProductFromCart(testProduct, requestHandler.getAllRequestLists().get(0));
        assertEquals(4, requestHandler.getAllRequestLists().get(0).getCart().size());
    }

    @Test
    public void testClearList(){
        requestHandler.clearList(requestHandler.getAllRequestLists().get(0));
        assertEquals(0, requestHandler.getAllRequestLists().get(0).getCart().size());
    }

    @Test
    public void testDeleteList(){
        requestHandler.deleteList(requestHandler.getAllRequestLists().get(0));
        assertEquals(5, requestHandler.getAllRequestLists().size());
    }

}
