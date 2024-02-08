package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;

import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.presentation.MainActivity;
import com.example.easyshopper.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityTest {

    // sleepTime used to add a delay any time we need to wait for the GUI
    private final int sleepTime = 500;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setUp() {
        testUtils = new TestUtils();
    }

    @Test
    public void testBottomNavigation() {
        // verify that the shopping list fragment is displayed initially
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        // verify that the search fragment is displayed after clicking the search icon
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));

        // click on the shopping list icon
        onView(withId(R.id.shoppingList)).perform(click());

        // verify that the search fragment is displayed after clicking the shopping list icon
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testItemPopup() {
        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        // verify that the search fragment is displayed after clicking the search icon
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));

        // getting the product at ID 1 to test against
        Product product = testUtils.getProductByID(1);

        // clicks on the first item in the productList
        onView(allOf(withId(R.id.productListView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(sleepTime);

        // checks if item name toolbar is in the right place
        onView(withId(R.id.ShoppingListToolBar)).check(matches(isDisplayed()));

        // check if toolbar name is correct
        onView(withId(R.id.searchedProductName)).check(matches(withText(product.getProductName())));

        // checks if nutritional fact text view is correct
        onView(withId(R.id.nutritionalFactsLabel)).check((matches(withText("Nutritional Facts"))));

        // verifies all nutritional facts are as expected
        onView(withId(R.id.searchedProductName)).check(matches(withText(product.getProductName())));
        onView(withId(R.id.caloriesLabel)).check(matches(withText("Calories: " + product.getCalories())));
        onView(withId(R.id.fatLabel)).check(matches(withText("Fat: " + product.getFat() + "g")));
        onView(withId(R.id.carbLabel)).check(matches(withText("Carbs: " + product.getCarb() + "g")));
        onView(withId(R.id.proteinLabel)).check(matches(withText("Protein: " + product.getProtein() + "g")));

        // checks if prices label text view is correct
        onView(withId(R.id.pricesLabel)).check((matches(withText("Current Prices"))));

        // check the prices at different stores
        onView(withId(R.id.pricePerStoreList)).check(matches(isDisplayed()));

        // check the productList recycled view
        onView(withId(R.id.pricePerStoreList)).check((view, noViewFoundException) -> {
            // check if product list recycled view is not null
            assertNotNull(view);

            // get the RecyclerView
            RecyclerView recyclerView = (RecyclerView) view;

            // get the adapter associated with the RecyclerView
            RecyclerView.Adapter adapter = recyclerView.getAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // get the list of all prices for the product
            List<Price> allSortedPrices = testUtils.allStoreSortedPrice(product);

            // check the number of items in the adapter (length of the RecyclerView list)
            int itemCount = adapter.getItemCount();
            int expectedCount = allSortedPrices.size();

            // assert that the RecyclerView list length is as expected
            assertEquals(expectedCount, itemCount);

            // iterate through the adapter's items to examine their contents
            for (int i = 0; i < adapter.getItemCount(); i++) {
                // get the item at position i from the adapter
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);

                // ensure that the ViewHolder is not null
                assertNotNull(viewHolder);

                // extract the productPrice and storeName from the ViewHolder
                TextView productNameTextView = viewHolder.itemView.findViewById(R.id.productPriceView);
                String productPrice = productNameTextView.getText().toString();

                TextView storeNameTextView = viewHolder.itemView.findViewById(R.id.productStoreView);
                String storeName = storeNameTextView.getText().toString();
                String expectedStoreName = testUtils.getStoreById(allSortedPrices.get(i).getStoreID()).getStoreName();


                // make sure both values match
                assertEquals("$" + allSortedPrices.get(i).getPrice(), productPrice);
                assertEquals(expectedStoreName, storeName);
            }
        });

        // TEST BACK BUTTON

        // click on the button
        onView(withId(R.id.itemPopUpBackBtn)).perform(click());

        // check if we go back to the search fragment
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchFragment() {
        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        // verify that the search fragment is displayed after clicking the search icon
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));

        // checks if the search bar is in the correct place
        onView(withId(R.id.searchContainer)).check(matches(isDisplayed()));

        // check if the productList recycled view is display
        onView(withId(R.id.productListView)).check(matches(isDisplayed()));

        // check the productList recycled view
        onView(withId(R.id.productListView)).check((view, noViewFoundException) -> {
            // check if product list recycled view is not null
            assertNotNull(view);

            // get the RecyclerView
            RecyclerView recyclerView = (RecyclerView) view;

            // get the adapter associated with the RecyclerView
            RecyclerView.Adapter adapter = recyclerView.getAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // get the list of all products
            List<Product> allProducts = testUtils.getAllProducts();

            // check the number of items in the adapter (length of the RecyclerView list)
            int itemCount = adapter.getItemCount();
            int expectedCount = allProducts.size();

            // assert that the RecyclerView list length is as expected
            assertEquals(expectedCount, itemCount);

            // iterate through the adapter's items to examine their contents
            for (int i = 0; i < adapter.getItemCount(); i++) {
                // get the item at position i from the adapter
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);

                // ensure that the ViewHolder is not null
                assertNotNull(viewHolder);

                // extract the productName from the ViewHolder
                TextView productNameTextView = viewHolder.itemView.findViewById(R.id.productNameView);
                String productName = productNameTextView.getText().toString();

                // make sure both values match
                assertEquals(allProducts.get(i).getProductName(), productName);
            }
        });

        // TEST SEARCH

        //Tests the UI to see if we get the Kiwi product at index 0 when we type "Kiwi" in the search bar
        onView(withId(R.id.searchView)).perform(typeText("Kiwi"));
        onView(allOf(withId(R.id.productListView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(sleepTime);

        onView(withId(R.id.searchedProductName)).check(matches(withText("Kiwi")));

        pressBack();
        SystemClock.sleep(sleepTime);
    }

    // TEST SHOPPING LIST FRAGMENT
    @Test
    public void testShoppingListFragment() {
        // click on the shopping list icon
        onView(withId(R.id.shoppingList)).perform(click());

        // verify that the search fragment is displayed after clicking the shopping list icon
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // checks if item name toolbar is in the right place
        onView(withId(R.id.ShoppingListToolBar)).check(matches(isDisplayed()));

        // check if the add button display
        onView(withId(R.id.addButton)).check(matches(isDisplayed()));

        // check if the expandable list view for shopping list display
        onView(withId(R.id.shoppingListView)).check(matches(isDisplayed()));

        // TEST THE EXPANDABLE SHOPPING LIST VIEW
        onView(withId(R.id.shoppingListView)).check((view, noViewFoundException) -> {
            // check if product list recycled view is not null
            assertNotNull(view);

            // get the ExpandableListView
            ExpandableListView expandableListView = (ExpandableListView) view;

            // get the adapter associated with the ExpandableListView
            ExpandableListAdapter adapter = expandableListView.getExpandableListAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // get all shopping list
            List<ShoppingList> allShoppingList = testUtils.getAllShoppingList();

            // check the length for the list view
            assertEquals(allShoppingList.size(), adapter.getGroupCount());

            for (int i = 0; i < adapter.getGroupCount(); i++) {
                // get the number of children before expanding
                //int childCountBefore = adapter.getChildrenCount(i);

                // get the product cart for shopping list
                ArrayList<Product> shoppingListCart = allShoppingList.get(i).getItemList();

                // get the shopping cart view
                View parentView = adapter.getGroupView(i, false, null, expandableListView);

                // check if it is not null
                assertNotNull(parentView);

                // check if the name and price for shopping cart is correct
                TextView shoppingCartName = parentView.findViewById(R.id.shopping_list_header_name);
                String cartName = shoppingCartName.getText().toString();
                String expectedCartName = allShoppingList.get(i).getShoppingListName();
                assertEquals(expectedCartName, cartName);

                TextView shoppingCartTotal = parentView.findViewById(R.id.shopping_list_price);
                String cartTotal = shoppingCartTotal.getText().toString();
                String expectedCartTotal = "$" + testUtils.getCartTotal(allShoppingList.get(i));
                assertEquals(expectedCartTotal, cartTotal);

                // expand the group
                expandableListView.expandGroup(i);

                int childCount = adapter.getChildrenCount(i);
                int expectedChildCount = shoppingListCart.size();

                // test if the length of each shopping list cart is correct
                assertEquals(expectedChildCount, childCount);

                // iterate through children of the group
                for (int j = 0; j < adapter.getChildrenCount(i); j++) {
                    View childView = adapter.getChildView(i, j, false, null, expandableListView);
                    assertNotNull(childView);

                    // check if product name and product price is correct
                    TextView productInCartName = childView.findViewById(R.id.productNameView);
                    String cartProductName = productInCartName.getText().toString();
                    String expectedCartProductName = shoppingListCart.get(j).getProductName();

                    assertEquals(expectedCartProductName, cartProductName);

                    TextView productInCartPrice = childView.findViewById(R.id.productPriceView);
                    String cartProductPrice = productInCartPrice.getText().toString();

                    // get the store and product of current child item
                    Store curStore = allShoppingList.get(i).getStore();
                    Product curProduct = shoppingListCart.get(j);

                    double expectedProductPrice = testUtils.getPriceOfProductInStore(curProduct, curStore);

                    assertEquals("$" + expectedProductPrice, cartProductPrice);
                }
            }
        });

        // TEST + BUTTON

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        // check if the menu and all items pop up
        onView(withText("Add product to list")).check(matches(isDisplayed()));
        onView(withText("Create a list")).check(matches(isDisplayed()));
        onView(withText("Delete a list")).check(matches(isDisplayed()));

        // TEST DELETE LIST
        onView(withText("Delete a list")).perform(click());

        // check if the delete list pop up appears
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // title
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Shopping Lists to Delete:")));

        // list of stores
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        onView(withId(R.id.list_view)).check((view, noViewFoundException) -> {
            // check if product list recycled view is not null
            assertNotNull(view);

            // get the ListView
            ListView listView = (ListView) view;

            // get the adapter associated with the RecyclerView
            ListAdapter adapter = listView.getAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // get the list of all stores
            List<ShoppingList> allStores = testUtils.getAllShoppingList();

            // check the number of stores in the list
            int storeCount = adapter.getCount();
            int expectedCount = allStores.size();

            // assert that the RecyclerView list length is as expected
            assertEquals(expectedCount, storeCount);

            // iterate through the adapter's items to examine their contents
            for (int i = 0; i < adapter.getCount(); i++) {
                // get the store at position i from the adapter
                Object store = adapter.getItem(i);

                // ensure that the ViewHolder is not null
                assertNotNull(store);
                assertTrue(store instanceof ShoppingList);

                ShoppingList curShoppingList = (ShoppingList) store;

                // make sure the check box is checked when clicked

                onData(anything())
                        .inAdapterView(withId(R.id.list_view)) // Replace with your ListView ID
                        .atPosition(i)
                        .perform(click());

                // assertTrue(listView.isItemChecked(i));
            }
        });

        // buttons
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click on the cancel button and check if it goes back to the shoppingListFragment
        onView(withId(R.id.cancel_btn)).perform(click());
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // TEST ADD PRODUCT

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        onView(withText("Add product to list")).perform(click());

        // check if the add product pop up appears
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // check the content inside the pop up

        // title
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Products to Add:")));

        // list of products
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        // buttons
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click on the cancel button and check if it goes back to the shoppingListFragment
        onView(withId(R.id.cancel_btn)).perform(click());
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));
    }

}