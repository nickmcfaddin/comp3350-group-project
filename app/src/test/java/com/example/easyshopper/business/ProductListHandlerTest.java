package com.example.easyshopper.business;

import static org.junit.Assert.assertEquals;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.logic.ProductListHandler;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductListHandlerTest {
    private Product product;

    @Before
    public void setup() {
        System.out.println("Starting test for RequestListHandler");
        boolean forProduction = false;

        ProductListHandler productListHandler = new ProductListHandler(forProduction);
        ShoppingListHandler shoppingListHandler = new ShoppingListHandler(forProduction);
        RequestListHandler requestHandler = new RequestListHandler(forProduction);

        product = new Product(10, "TestProduct", 0.1, 0.2, 0.3, 1);
    }

    @Test
    public void testItemAddAndRemoveShoppingList() {
        // there are 3 shopping lists with no items by default
        ProductListHandler.addProductToCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(1, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
        assertEquals(10, ShoppingListHandler.getAllShoppingLists().get(0).getCart().get(0).getProductID());

        ProductListHandler.removeProductFromCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.addProductToCart(null, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        // ProductListHandler.addProductToCart(product, null);
        //assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.removeProductFromCart(null, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        //ProductListHandler.removeProductFromCart(product, null);
        //assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
    }

    @Test
    public void testItemAddAndRemoveRequestList() {
        // there are 5 request lists with 4 items each by default
        ProductListHandler.addProductToCart(product, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(5, RequestListHandler.getAllRequestLists().get(0).getCart().size());
        assertEquals(10, RequestListHandler.getAllRequestLists().get(0).getCart().get(4).getProductID());

        ProductListHandler.removeProductFromCart(product, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(4, RequestListHandler.getAllRequestLists().get(0).getCart().size());

        ProductListHandler.addProductToCart(null, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(4, RequestListHandler.getAllRequestLists().get(0).getCart().size());

        //ProductListHandler.addProductToCart(product, null);
        //assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());

        ProductListHandler.removeProductFromCart(null, RequestListHandler.getAllRequestLists().get(0));
        assertEquals(4, RequestListHandler.getAllRequestLists().get(0).getCart().size());

        //ProductListHandler.removeProductFromCart(product, null);
        //assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
    }

    @Test
    public void testDeleteClearShoppingList() {
        // there are 3 shopping lists with no items by default
        ProductListHandler.deleteList(ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(2, ShoppingListHandler.getAllShoppingLists().size());

        // add product to cart then clear it
        ProductListHandler.addProductToCart(product, ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(1, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
        assertEquals(10, ShoppingListHandler.getAllShoppingLists().get(0).getCart().get(0).getProductID());

        ProductListHandler.clearList(ShoppingListHandler.getAllShoppingLists().get(0));
        assertEquals(2, ShoppingListHandler.getAllShoppingLists().size());
        assertEquals(0, ShoppingListHandler.getAllShoppingLists().get(0).getCart().size());
    }

    @Test
    public void testDeleteClearRequestList() {
        // there are 5 request lists with 4 items each by default
        ProductListHandler.deleteList(RequestListHandler.getAllRequestLists().get(0));
        assertEquals(4, RequestListHandler.getAllRequestLists().size());

        ProductListHandler.clearList(RequestListHandler.getAllRequestLists().get(0));
        assertEquals(4, RequestListHandler.getAllRequestLists().size());
        assertEquals(0, RequestListHandler.getAllRequestLists().get(0).getCart().size());
    }

    @After
    public void tearDown(){
        System.out.println("Reset database.");

        Services.clean();
    }
}
