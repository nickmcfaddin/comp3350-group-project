package com.example.easyshopper;

import androidx.test.core.app.ActivityScenario;
// import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.easyshopper.presentation.SearchFragment;
import com.example.easyshopper.utils.TestUtils;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ItemPopUpTest {
    private final int sleeptime = 500;

    // public ActivityTestRule

    private TestUtils testUtils;

    @Before
    public void setUpTestUtils(){
        // ActivityScenario.launch(SearchFragment.class);
        testUtils = new TestUtils();
    };



}
