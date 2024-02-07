package com.example.easyshopper;

import static androidx.core.os.BundleKt.bundleOf;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import androidx.fragment.app.testing.FragmentScenario;


import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.easyshopper.objects.Product;
import com.example.easyshopper.presentation.MainActivity;
import com.example.easyshopper.presentation.SearchFragment;
import com.example.easyshopper.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchFragmentTest {
    private final int sleepTime = 500;

    @Rule
    public FragmentScenario<SearchFragment> searchFragmentScenario;
    private TestUtils testUtils;

    public SearchFragmentTest() {
        searchFragmentScenario = FragmentScenario.launchInContainer(SearchFragment.class);
    }

    @Before
    public void setupTestUtils() {testUtils = new TestUtils();}

    @Test
    public void viewProductsUnderSearchTest(){
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerView);
        List<Product> allProducts = testUtils.getAllProducts();

        val scenario = launchFragmentInContainer<EventFragment>();
        onView(withId(R.id.refresh)).perform(click();

        // onView(allOf(withId(R.id.productListView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


    }
