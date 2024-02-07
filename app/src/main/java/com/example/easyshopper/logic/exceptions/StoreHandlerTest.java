package com.example.easyshopper.logic.exceptions;

import com.example.easyshopper.logic.StoreHandler;

public class StoreHandlerTest {
    public void setup(){
        System.out.println("Start test StoreHandler");
    }

    public void test(){
        StoreHandler temp = new StoreHandler();
        System.out.println("Start test getStoreById");
        assert(temp.getStoreById(1) != null);
        System.out.println("Pass");

        System.out.println("Start test getExistingStores");
        assert(temp.getExistingStores() != null);
        System.out.println("Pass");
    }
}
