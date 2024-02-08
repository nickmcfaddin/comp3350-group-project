package com.example.easyshopper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.easyshopper.business.ProductHandlerTest;
import com.example.easyshopper.business.ShoppingListHandlerTest;
import com.example.easyshopper.business.StoreHandlerTest;
import com.example.easyshopper.objects.PriceTest;
import com.example.easyshopper.objects.ProductTest;
import com.example.easyshopper.objects.ShoppingListUnitTest;
import com.example.easyshopper.objects.StoreTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        //Logic Tests
        ProductHandlerTest.class,
        ShoppingListHandlerTest.class,
        StoreHandlerTest.class,

        //Object Tests
        PriceTest.class,
        ProductTest.class,
        ShoppingListUnitTest.class,
        StoreTest.class
})

public class AllUnitTests{}