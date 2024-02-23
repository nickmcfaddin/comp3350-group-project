package com.example.easyshopper.logic;

import com.example.easyshopper.application.Services;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.persistence.HomeProductPersistence;

import java.io.Serializable;
import java.util.List;

public class HomeProductHandler implements Serializable {
    private HomeProductPersistence homeProductPersistence = Services.getHomeProductPersistence();

    public HomeProductHandler(){}

    /*
     * method gets a list of all home products initialized in the database
     */
    public List<HomeProduct> getAllHomeProduct()
    {
        return homeProductPersistence.getExistingHomeProducts();
    }

    public HomeProduct getHomeProductByID(int id)
    {
        if(id < 0) {
            return null;
        }

        return homeProductPersistence.getHomeProductById(id);
    }
}

