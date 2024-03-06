package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ProductListHandler;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ProductListHandlerIT {
    private Product product;
    private User user;
    private Store store;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting test for RequestListHandler");
        Services.clean();
        this.tempDB = TestUtils.copyDB();

        boolean forProduction = true;

        ProductListHandler productListHandler = new ProductListHandler(forProduction);
        ShoppingListHandler shoppingListHandler = new ShoppingListHandler(forProduction);
        RequestListHandler requestHandler = new RequestListHandler(forProduction);
        StoreHandler storeHandler = new StoreHandler(forProduction);
        UserHandler userHandler = new UserHandler(forProduction);

        product = new Product(4, "Peanut Butter", 25.1, 20.9, 50.3, 180);
        store = new Store(1, "Loblaws");
        user = UserHandler.createUser("Tester");
    }

    @Test
    public void testShoppingListFunctions() {
        //Create shopping list
        ShoppingListHandler.createShoppingList(store);

        //Add product to shopping list
        ProductListHandler.addProductToCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(1, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
        assertEquals(4, ShoppingListHandler.getAllShoppingLists().get(0).getCart().get(0).getProductID());

        //Remove product from shopping list
        ProductListHandler.removeProductFromCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        //Test that null adds/removes do nothing
        ProductListHandler.addProductToCart(null, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.removeProductFromCart(null, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        //Test delete function
        ProductListHandler.deleteList(ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().size());

        //Test product clear function
        ShoppingListHandler.createShoppingList(store);
        ProductListHandler.addProductToCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        ProductListHandler.clearList(ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
    }

    @Test
    public void testRequestListFunctions() {
        //Create request list
        RequestListHandler.createRequestList(user);
        System.out.println("Request lists: " + RequestListHandler.getAllRequestLists());

        //Add product to requestList
        ProductListHandler.addProductToCart(product, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(1, RequestListHandler.getAllRequestLists().get(0).getCart().size());
        assertEquals(4, RequestListHandler.getAllRequestLists().get(0).getCart().get(0).getProductID());

        //Remove product from requestList
        ProductListHandler.removeProductFromCart(product, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(0, RequestListHandler.getAllRequestLists().get(0).getCart().size());

        //Test that null adds/removes do nothing
        ProductListHandler.addProductToCart(null, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(0, RequestListHandler.getAllRequestLists().get(0).getCart().size());
        ProductListHandler.removeProductFromCart(null, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(0, RequestListHandler.getAllRequestLists().get(0).getCart().size());

        //Test delete function
        ProductListHandler.deleteList(RequestListHandler.getAllRequestLists().get(0));
        assertEquals(0, RequestListHandler.getAllRequestLists().size());

        //Create request list
        RequestListHandler.createRequestList(user);

        //Tests clear function
        ProductListHandler.clearList(RequestListHandler.getAllRequestLists().get(0));
        assertEquals(0, RequestListHandler.getAllRequestLists().get(0).getCart().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }
}

