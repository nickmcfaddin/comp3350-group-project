package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import android.os.SystemClock;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;

import com.example.easyshopper.presentation.MainActivity;
import com.example.easyshopper.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchFragmentTest {
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
    public void testSearchFragmentUI() {
        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        SystemClock.sleep(sleepTime);

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
    }

    @Test
    public void testSearchFragmentSearchBar() {
        //Tests the UI to see if we get the Kiwi product at index 0 when we type "Kiwi" in the search bar

        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the search fragment is displayed after clicking the search icon
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));

        onView(withId(R.id.searchView)).perform(typeText("Kiwi"));
        onView(allOf(withId(R.id.productListView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.searchedProductName)).check(matches(withText("Kiwi")));
    }

    @Test
    public void testSearchFragmentItemPopUp() {
        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        SystemClock.sleep(sleepTime);

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

            // test one adapter item (in this case, the one at index 0) and its contents
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);

            // ensure that the ViewHolder is not null
            assertNotNull(viewHolder);

            // extract the productPrice and storeName from the ViewHolder
            TextView productNameTextView = viewHolder.itemView.findViewById(R.id.productPriceView);
            String productPrice = productNameTextView.getText().toString();

            TextView storeNameTextView = viewHolder.itemView.findViewById(R.id.productStoreView);
            String storeName = storeNameTextView.getText().toString();
            String expectedStoreName = testUtils.getStoreById(allSortedPrices.get(0).getStoreID()).getStoreName();

            // make sure both values match
            assertEquals("$" + allSortedPrices.get(0).getPrice(), productPrice);
            assertEquals(expectedStoreName, storeName);
        });

        // TEST BACK BUTTON
        onView(withId(R.id.itemPopUpBackBtn)).check(matches(isDisplayed()));

        // click on the button
        onView(withId(R.id.itemPopUpBackBtn)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if we go back to the search fragment
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));
    }
}