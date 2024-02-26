package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.HomeProduct;

import java.util.List;

public interface HomeProductPersistence {
    //Returns a list of all HomeProduct's that exist
    List<HomeProduct> getExistingHomeProducts();

    //Returns a single Product type identified by its ProductID
    HomeProduct getHomeProductById(int productID);
}
