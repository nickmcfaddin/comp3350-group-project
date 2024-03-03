package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.easyshopper.presentation.TestStubDBMainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    // sleepTime used to add a delay any time we need to wait for the GUI
    private final int sleepTime = 500;

    @Rule
    public ActivityScenarioRule<TestStubDBMainActivity> activityScenarioRule = new ActivityScenarioRule<>(TestStubDBMainActivity.class);

    @Test
    public void testBottomNavigation() {
        // verify that the shopping list fragment is displayed initially
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // click on the home inventory icon
        onView(withId(R.id.homeInventory)).perform(click());

        // verify that the home inventory fragment is displayed after clicking the home inventory icon
        onView(withId(R.id.HomeInventoryFragment)).check(matches(isDisplayed()));

        SystemClock.sleep(sleepTime);

        // click on the search icon
        onView(withId(R.id.search)).perform(click());

        // verify that the search fragment is displayed after clicking the search icon
        onView(withId(R.id.SearchFragment)).check(matches(isDisplayed()));

        SystemClock.sleep(sleepTime);

        // click on the user request icon

        // verify that the home user request fragment is displayed after clicking the user request icon

        // click on the shopping list icon
        onView(withId(R.id.shoppingList)).perform(click());

        // verify that the shopping list fragment is displayed after clicking the shopping list icon
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));
    }
}