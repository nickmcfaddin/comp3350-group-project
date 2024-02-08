package com.example.easyshopper;

import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@LargeTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        SearchFragmentTest.class,
        ShoppingListFragmentTest.class
})

public class AllAcceptanceTests {}
