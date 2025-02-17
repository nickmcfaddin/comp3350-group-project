package com.example.easyshopper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.easyshopper.business.HomeInventoryHandlerIT;
import com.example.easyshopper.business.ProductHandlerIT;
import com.example.easyshopper.business.RequestListHandlerIT;
import com.example.easyshopper.business.ShoppingListHandlerIT;
import com.example.easyshopper.business.StoreHandlerIT;
import com.example.easyshopper.business.UserHandlerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProductHandlerIT.class,
        ShoppingListHandlerIT.class,
        StoreHandlerIT.class,
        HomeInventoryHandlerIT.class,
        RequestListHandlerIT.class,
        UserHandlerIT.class
})

public class AllIntegrationTests{}