package com.example.easyshopper;

import androidx.fragment.app.Fragment;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.core.app.ActivityScenario;

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

    ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

    @Test
    public void testNavigation() {
        // Verify that the shopping list fragment is displayed initially
        onView(withId(R.id.ShoppingListFragment)).check(matches(isDisplayed()));

        // Click on the home inventory tab
        onView(withId(R.id.homeInventory)).perform(click());

        // Verify that the home fragment is displayed after clicking the home inventory tab
        onView(withId(R.id.HomeFragment)).check(matches(isDisplayed()));

        // Click on the search tab
        onView(withId(R.id.search)).perform(click());

        // Verify that the search fragment is displayed after clicking the search tab
        onView(withId(R.id.searchFragment)).check(matches(isDisplayed()));
    }
}