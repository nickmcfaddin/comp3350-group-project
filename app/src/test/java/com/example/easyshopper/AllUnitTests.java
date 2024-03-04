package com.example.easyshopper;

import com.example.easyshopper.business.ProductHandlerTest;
import com.example.easyshopper.business.ShoppingListHandlerTest;
import com.example.easyshopper.business.StoreHandlerTest;
import com.example.easyshopper.business.HomeInventoryHandlerTest;
import com.example.easyshopper.objects.HomeProductTest;
import com.example.easyshopper.objects.PriceTest;
import com.example.easyshopper.objects.ProductTest;
import com.example.easyshopper.objects.RequestListTest;
import com.example.easyshopper.objects.ShoppingListUnitTest;
import com.example.easyshopper.objects.StoreTest;
import com.example.easyshopper.objects.UserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        //Logic Tests
        ProductHandlerTest.class,
        ShoppingListHandlerTest.class,
        StoreHandlerTest.class,
        HomeInventoryHandlerTest.class,

        //Object Tests
        PriceTest.class,
        ProductTest.class,
        ShoppingListUnitTest.class,
        StoreTest.class,
        HomeProductTest.class,
        RequestListTest.class,
        UserTest.class
})

public class AllUnitTests{}