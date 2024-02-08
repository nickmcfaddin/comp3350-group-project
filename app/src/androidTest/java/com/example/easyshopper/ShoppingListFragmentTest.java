package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import android.os.SystemClock;
import android.view.View;
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
public class ShoppingListFragmentTest {
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
    public void testShoppingListFragmentUI() {
        // click on the shopping list icon
        onView(withId(R.id.shoppingList)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the search fragment is displayed after clicking the shopping list icon
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // checks if item name toolbar is in the right place
        onView(withId(R.id.ShoppingListToolBar)).check(matches(isDisplayed()));

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

                SystemClock.sleep(sleepTime);

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

        // check if the add button display
        onView(withId(R.id.addButton)).check(matches(isDisplayed()));

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Add product to list")).check(matches(isDisplayed()));
        onView(withText("Create a list")).check(matches(isDisplayed()));
        onView(withText("Delete a list")).check(matches(isDisplayed()));
    }

    @Test
    public void testShoppingListFragmentDeleteList() {
        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

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
        });

        // buttons
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click on the cancel button and check if it goes back to the shoppingListFragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testShoppingListFragmentAddProduct() {
        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Add product to list")).check(matches(isDisplayed()));
        onView(withText("Create a list")).check(matches(isDisplayed()));
        onView(withText("Delete a list")).check(matches(isDisplayed()));

        // click addProduct
        onView(withText("Add product to list")).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the add product pop up appears
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // check the content inside the pop up

        // title
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Products to Add:")));

        // list of products
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

            // get the list of all products
            List<Product> allProducts = testUtils.getAllProducts();

            // check the number of stores in the list
            int productCount = adapter.getCount();
            int expectedCount = allProducts.size();

            // assert that the RecyclerView list length is as expected
            assertEquals(expectedCount, productCount);
        });

        // buttons
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click on the cancel button and check if it goes back to the shoppingListFragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testShoppingListFragmentCreateList() {
        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Add product to list")).check(matches(isDisplayed()));
        onView(withText("Create a list")).check(matches(isDisplayed()));
        onView(withText("Delete a list")).check(matches(isDisplayed()));

        // click on createList
        onView(withText("Create a list")).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the create list pop up appears
        onView(withId(R.id.createListPopUp)).check(matches(isDisplayed()));

        // check the content inside the pop up

        // title
        onView(withId(R.id.store_select_title)).check(matches(isDisplayed()));

        onView(withId(R.id.store_select_title)).check(matches(withText("Select Affiliated Store:")));

        // button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click on the cancel button and check if it goes back to the shoppingListFragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // get current ShoppingList length and number of stores
        int beforeShoppingListLen = testUtils.getAllShoppingList().size();
        int numberOfStores = testUtils.getExistingStores().size();

        // go back to the create a list pop up
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withText("Create a list")).perform(click());

        SystemClock.sleep(sleepTime);

        // check submit button without choosing any store for dropdown
        onView(withId(R.id.submit_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the create list pop up is still displayed
        onView(withId(R.id.createListPopUp)).check(matches(isDisplayed()));

        // store dropdown test
        onView(withId(R.id.textInputLayout)).check(matches(isDisplayed()));

        SystemClock.sleep(sleepTime);

        List<Store> allStores = testUtils.getExistingStores();

        // check dropdown menu store name and interaction with dropdown menu's items
        for (int i = 0; i < allStores.size(); i++) {
            // click the dropdown
            onView(withId(R.id.textInputLayout)).perform(click());

            // check if the values in dropdown menu is correct
            onData(anything())
                    .inRoot(isPlatformPopup())
                    .atPosition(i)
                    .check(matches(withText(allStores.get(i).getStoreName())));

            SystemClock.sleep(sleepTime);

            // click on dropdown item
            onData(anything())
                    .inRoot(isPlatformPopup())
                    .atPosition(i)
                    .perform(click());

            SystemClock.sleep(sleepTime);

            // check if the text input change
            onView(allOf(withId(R.id.dropdown_field), isDescendantOfA(withId(R.id.textInputLayout))))
                    .check(matches(withText(allStores.get(i).getStoreName())));
        }

        // check submit button when store is chosen
        onView(withId(R.id.submit_btn)).perform(click());

        // check if it goes back to the shoppingListFragment
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // check if shopping list change or not
        // if the number of shopping list is equal to number of stores before adding the new shopping list
        // shopping list will stay the same because it won't allow duplicate store shopping list
        if (beforeShoppingListLen == numberOfStores) {
            // get all shopping list
            List<ShoppingList> afterAllShoppingList = testUtils.getAllShoppingList();

            // check the length for the list view
            assertEquals(beforeShoppingListLen, afterAllShoppingList.size());
        }
    }
}
