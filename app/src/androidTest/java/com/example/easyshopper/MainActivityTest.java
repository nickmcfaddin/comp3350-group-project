package com.example.easyshopper;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.easyshopper.presentation.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

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
}