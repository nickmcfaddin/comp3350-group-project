package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;

import android.os.SystemClock;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
public class MainActivityTest {

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

        // click on the home inventory icon
        onView(withId(R.id.homeInventory)).perform(click());

        // verify that the home fragment is displayed after clicking the home inventory icon
        onView(withId(R.id.HomeFragment)).check(matches(isDisplayed()));

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

        // getting the product at ID 1 to test against
        Product product = testUtils.getProductByID(1);

        // clicks on the first item in the productList
        onView(allOf(withId(R.id.productListView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(sleepTime);

        // checks if item name toolbar is in the right place
        onView(withId(R.id.ShoppingListToolBar)).check(matches(isDisplayed()));

        // verifies all nutritional facts are as expected
        onView(withId(R.id.searchedProductName)).check(matches(withText(product.getProductName())));
        onView(withId(R.id.caloriesLabel)).check(matches(withText("Calories: " + product.getCalories())));
        onView(withId(R.id.fatLabel)).check(matches(withText("Fat: " + product.getFat() + "g")));
        onView(withId(R.id.carbLabel)).check(matches(withText("Carbs: " + product.getCarb() + "g")));
        onView(withId(R.id.proteinLabel)).check(matches(withText("Protein: " + product.getProtein() + "g")));

        //check the prices at different stores
    }

    @Test
    public void testSearchFragment() {
        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        // verify that the search fragment is displayed after clicking the search icon
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));

        // checks if the search bar is in the correct place
        onView(withId(R.id.searchContainer)).check(matches(isDisplayed()));

        // check if the recycled view for productList is not null
        onView(withId(R.id.productListView)).check((view, noViewFoundException) -> {
            assertNotNull(view);
        });



        //Tests the UI to see if we get the Kiwi product at index 0 when we type "Kiwi" in the search bar
        onView(withId(R.id.searchView)).perform(typeText("Kiwi"));
        onView(allOf(withId(R.id.productListView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(sleepTime);

        onView(withId(R.id.searchedProductName)).check(matches(withText("Kiwi")));

        pressBack();
        SystemClock.sleep(sleepTime);
    }
}