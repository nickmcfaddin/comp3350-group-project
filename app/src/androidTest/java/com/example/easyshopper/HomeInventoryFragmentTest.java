package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import android.os.SystemClock;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.presentation.TestStubDBMainActivity;
import com.example.easyshopper.utils.TestUtils;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeInventoryFragmentTest {
    // sleepTime used to add a delay any time we need to wait for the GUI
    private final int sleepTime = 500;

    @Rule
    public ActivityScenarioRule<TestStubDBMainActivity> activityScenarioRule = new ActivityScenarioRule<>(TestStubDBMainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setUp() {
        testUtils = new TestUtils(false);
    }

    @Test
    public void testHomeInventoryFragmentUI() {
        // click on the home inventory icon
        onView(withId(R.id.homeInventory)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the home inventory fragment is displayed after clicking the home inventory icon
        onView(withId(R.id.HomeInventoryFragment)).check(matches(isDisplayed()));

        // checks if item name toolbar is in the right place
        onView(withId(R.id.HomeInventoryToolBar)).check(matches(isDisplayed()));

        // check if the sort button is shown
        onView(withId(R.id.sortButton)).check(matches(isDisplayed()));

        // check if the columns of table is shown
        onView(withId(R.id.tableLayout)).check(matches(isDisplayed()));

        // check if the recycled view for home inventory stock display
        onView(withId(R.id.stockHomeInventoryView)).check(matches(isDisplayed()));

        // check the title for hidden product view
        onView(withId(R.id.hiddenProductLayout)).check(matches(isDisplayed()));

        // check if the recycled view for home inventory hidden not display
        onView(withId(R.id.hiddenHomeInventoryView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // get all stock and hidden product
        List<HomeProduct> allStockProducts = testUtils.getStockProduct();
        List<HomeProduct> allHiddenProducts = testUtils.getHiddenProduct();

        // TEST THE HOME INVENTORY STOCK VIEW
        checkContentOfRecycledView(R.id.stockHomeInventoryView, allStockProducts);

        // TEST THE HOME INVENTORY HIDDEN VIEW

        // click on the hiddenProductLayout
        onView(withId(R.id.hiddenProductLayout)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the recycled view for home inventory hidden display after
        onView(withId(R.id.hiddenHomeInventoryView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        checkContentOfRecycledView(R.id.hiddenHomeInventoryView, allHiddenProducts);
    }

    @Test
    public void testHomeProductExpiryDatePopUp(){
        // click on the home inventory icon
        onView(withId(R.id.homeInventory)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the home inventory fragment is displayed after clicking the home inventory icon
        onView(withId(R.id.HomeInventoryFragment)).check(matches(isDisplayed()));

        // get all stock and hidden product
        List<HomeProduct> allStockProducts = testUtils.getStockProduct();
        List<HomeProduct> allHiddenProducts = testUtils.getHiddenProduct();

        // check if the recycled view for home inventory stock display
        onView(withId(R.id.stockHomeInventoryView)).check(matches(isDisplayed()));

        // PERFORM EXPIRY DATE TEST ON THE STOCK PRODUCTS
        checkContentOfExpiryDateListView(R.id.stockHomeInventoryView, R.id.list_view, allStockProducts);

        // click on the hiddenProductLayout
        onView(withId(R.id.hiddenProductLayout)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the recycled view for home inventory hidden display after
        onView(withId(R.id.hiddenHomeInventoryView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // PERFORM EXPIRY DATE TEST ON THE HIDDEN PRODUCTS
        checkContentOfExpiryDateListView(R.id.hiddenHomeInventoryView, R.id.list_view, allHiddenProducts);
    }

    @Test
    public void testHomeInventoryFragmentHomeProductButton(){
        // click on the home inventory icon
        onView(withId(R.id.homeInventory)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the home inventory fragment is displayed after clicking the home inventory icon
        onView(withId(R.id.HomeInventoryFragment)).check(matches(isDisplayed()));

        // check if the recycled view for home inventory display
        onView(withId(R.id.stockHomeInventoryView)).check(matches(isDisplayed()));

        // click on the hiddenProductLayout
        onView(withId(R.id.hiddenProductLayout)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the recycled view for home inventory hidden display after
        onView(withId(R.id.hiddenHomeInventoryView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // test buttons for stock home products
        checkContentAfterClickingHomeProductButton(R.id.stockHomeInventoryView, R.id.addStockQuantity);
        checkContentAfterClickingHomeProductButton(R.id.stockHomeInventoryView, R.id.removeStockQuantity);
        checkContentAfterClickingHomeProductButton(R.id.stockHomeInventoryView, R.id.addDesiredQuantity);
        checkContentAfterClickingHomeProductButton(R.id.stockHomeInventoryView, R.id.removeDesiredQuantity);

        // test buttons for hidden home products
        checkContentAfterClickingHomeProductButton(R.id.hiddenHomeInventoryView, R.id.addStockQuantity);
        checkContentAfterClickingHomeProductButton(R.id.hiddenHomeInventoryView, R.id.addDesiredQuantity);
    }

    @Test
    public void testHomeInventoryFragmentSortButton(){
        // click on the home inventory icon
        onView(withId(R.id.homeInventory)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the home inventory fragment is displayed after clicking the home inventory icon
        onView(withId(R.id.HomeInventoryFragment)).check(matches(isDisplayed()));

        // click on the sortButton
        onView(withId(R.id.sortButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the recycled view for home inventory stock still display
        onView(withId(R.id.stockHomeInventoryView)).check(matches(isDisplayed()));

        // get all sorted stock product
        List<HomeProduct> allSortedStockProducts = testUtils.getSortedStockProduct();

        // TEST THE HOME INVENTORY STOCK VIEW
        checkContentOfRecycledView(R.id.stockHomeInventoryView, allSortedStockProducts);
    }

    // HELPER METHODS
    private void checkContentOfExpiryDateListView(int allProductListViewID, int productExpiryDateListViewID, List<HomeProduct> homeProducts){
        for (int i=0; i<homeProducts.size(); i++){
            onView(withId(allProductListViewID)).perform(actionOnItemAtPosition(i, clickOnViewChildWithId(R.id.homeProductName)));

            // check if the expiry date popup display
            onView(withId(R.id.showAllExpiryDatesPopUp)).check(matches(isDisplayed()));

            // check if the contents of pop up shows
            onView(withId(R.id.home_product_expiry_dates)).check(matches(isDisplayed()));
            onView(withId(R.id.home_product_life_time_info)).check(matches(isDisplayed()));
            onView(withId(R.id.sortExpiryDateAscendingBtn)).check(matches(isDisplayed()));
            onView(withId(R.id.sortExpiryDateDescendingBtn)).check(matches(isDisplayed()));
            onView(withId(R.id.close_btn)).check(matches(isDisplayed()));
            onView(withId(R.id.list_view)).check(matches(isDisplayed()));

            // get expiry date of the first product in stock
            List<String> homeProductExpiryDates = testUtils.getHomeProductExpiryDates(homeProducts.get(i));
            List<String> homeProductExpiryDatesAscending = testUtils.getHomeProductSortedExpiryDatesAscending(homeProducts.get(i));
            List<String> homeProductExpiryDatesDescending = testUtils.getHomeProductSortedExpiryDatesDescending(homeProducts.get(i));

            // check content of list view
            checkContentOfExpiryDateSingleItemListView(productExpiryDateListViewID, homeProductExpiryDates);

            // click on sort button ascending
            onView(withId(R.id.sortExpiryDateAscendingBtn)).perform(click());

            SystemClock.sleep(sleepTime);

            // check content of list view
            checkContentOfExpiryDateSingleItemListView(productExpiryDateListViewID, homeProductExpiryDatesAscending);

            // click on sort button descending
            onView(withId(R.id.sortExpiryDateDescendingBtn)).perform(click());

            SystemClock.sleep(sleepTime);

            // check content of list view
            checkContentOfExpiryDateSingleItemListView(productExpiryDateListViewID, homeProductExpiryDatesDescending);

            // click on the close button
            onView(withId(R.id.close_btn)).perform(click());

            SystemClock.sleep(sleepTime);

            // check if the homeInventoryFragment is shown
            onView(withId(R.id.HomeInventoryFragment)).check(matches(isDisplayed()));
        }
    }

    private void checkContentOfExpiryDateSingleItemListView(int listViewID, List<String> list){
        onView(withId(listViewID)).check((view, noViewFoundException) -> {
            // Get the ListView
            ListView listView = (ListView) view;

            // Get the adapter associated with the ListView
            ListAdapter adapter = listView.getAdapter();

            // Check if the adapter is not null
            assertNotNull(adapter);

            // check the length for the list view
            assertEquals(list.size(), adapter.getCount());

            // Iterate through the adapter to get the contents of each item
            for (int i = 0; i < adapter.getCount(); i++) {
                // Get the view associated with the current item
                View listItemView = listView.getChildAt(i);

                // Find the TextView in the list item layout
                TextView textView = listItemView.findViewById(android.R.id.text1);

                // Get the text from the TextView and perform assertions
                String text = textView.getText().toString();

                assertEquals(list.get(i), text);
            }
        });
    }

    private void checkContentOfRecycledView(int recycledViewID, List<HomeProduct> list){
        // get the adapter associated with the RecyclerView
        onView(withId(recycledViewID)).check((view, noViewFoundException) -> {
            // check if recycled view is not null
            assertNotNull(view);

            // get the RecyclerView
            RecyclerView stockHomeProductView = (RecyclerView) view;

            RecyclerView.Adapter<?> adapter = ((RecyclerView) stockHomeProductView).getAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // check the length for the list view
            assertEquals(list.size(), adapter.getItemCount());

            // check if the home product name, stock and desired quantity is correct
            for (int i = 0; i < adapter.getItemCount(); i++){
                // get the item at position i from the adapter
                RecyclerView.ViewHolder viewHolder = ((RecyclerView) view).findViewHolderForAdapterPosition(i);

                // check the view holder not null
                assertNotNull(viewHolder);

                // check if the home product name, stock and desired quantity is correct
                TextView homeProductNameTextView = viewHolder.itemView.findViewById(R.id.homeProductName);
                String homeProductName = homeProductNameTextView.getText().toString();
                String expectedHomeProductName = list.get(i).getProductName();

                TextView homeProductStockQuantityTextView = viewHolder.itemView.findViewById(R.id.homeProductStockQuantity);
                String homeProductStockQuantity = homeProductStockQuantityTextView.getText().toString();
                String expectedHomeProductStockQuantity = String.valueOf(list.get(i).getHomeProductStockQuantity());

                TextView homeProductDesiredQuantityTextView = viewHolder.itemView.findViewById(R.id.homeProductDesiredQuantity);
                String homeProductDesiredQuantity = homeProductDesiredQuantityTextView.getText().toString();
                String expectedHomeProductDesiredQuantity = String.valueOf(list.get(i).getHomeProductDesiredQuantity());

                assertEquals(expectedHomeProductName, homeProductName);
                assertEquals(expectedHomeProductStockQuantity, homeProductStockQuantity);
                assertEquals(expectedHomeProductDesiredQuantity, homeProductDesiredQuantity);
            }
        });
    }


    private void checkContentAfterClickingHomeProductButton(int recycledViewID, int buttonID){
        // get all stock and hidden product
        List<HomeProduct> allStockProducts = testUtils.getStockProduct();
        List<HomeProduct> allHiddenProducts = testUtils.getHiddenProduct();
        List<HomeProduct> testingList = null;

        if (recycledViewID == R.id.stockHomeInventoryView){
            testingList = allStockProducts;
        }
        else if (recycledViewID == R.id.hiddenHomeInventoryView){
            testingList = allHiddenProducts;
        }

        if (testingList != null && testingList.size() != 0) {
            int i = 0;

            while (i < testingList.size()) {
                // click on add stock quantity button
                onView(withId(recycledViewID)).perform(actionOnItemAtPosition(i, clickOnViewChildWithId(buttonID)));

                SystemClock.sleep(sleepTime);

                allStockProducts = testUtils.getStockProduct();
                allHiddenProducts = testUtils.getHiddenProduct();

                // check the content of recycled view again
                checkContentOfRecycledView(R.id.stockHomeInventoryView, allStockProducts);
                checkContentOfRecycledView(R.id.hiddenHomeInventoryView, allHiddenProducts);

                // Move to the next index
                i++;

                int oldListLength = testingList.size();

                // Update testingList size after each iteration
                if (recycledViewID == R.id.stockHomeInventoryView){
                    testingList = allStockProducts;
                }
                else if (recycledViewID == R.id.hiddenHomeInventoryView){
                    testingList = allHiddenProducts;
                }

                if (testingList.size() < oldListLength){
                    i = 0;
                }
            }
        }
    }

    public static ViewAction clickOnViewChildWithId(@IdRes int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(RecyclerView.class));
            }

            @Override
            public String getDescription() {
                return "Click on child view with id: " + id;
            }

            @Override
            public void perform(UiController uiController, View view) {
                // Find the child view within the itemView
                View childView = view.findViewById(id);
                // Perform a click on the child view
                childView.performClick();
            }
        };
    }
}
