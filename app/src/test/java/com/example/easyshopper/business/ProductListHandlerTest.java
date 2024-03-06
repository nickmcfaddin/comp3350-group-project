package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ProductListHandler;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductListHandlerTest {
    private Product product;
    private User testUser;

    @Before
    public void setup() {
        System.out.println("Starting test for RequestListHandler");
        boolean forProduction = false;

        ProductListHandler productListHandler = new ProductListHandler(forProduction);
        ShoppingListHandler shoppingListHandler = new ShoppingListHandler(forProduction);
        RequestListHandler requestHandler = new RequestListHandler(forProduction);

        testUser = new User("George", "10");
        product = new Product(10, "TestProduct", 0.1, 0.2, 0.3, 1);
    }

    @Test
    public void testItemAddAndRemove() {
        // there are 3 shopping lists with no items by default
        ProductListHandler.addProductToCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(1, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
        assertEquals(10, ShoppingListHandler.getAllShoppingLists().get(0).getCart().get(0).getProductID());

        ProductListHandler.removeProductFromCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.addProductToCart(null, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.addProductToCart(product, null);
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.removeProductFromCart(null, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.removeProductFromCart(product, null);
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");

        Services.clean();
    }
}
