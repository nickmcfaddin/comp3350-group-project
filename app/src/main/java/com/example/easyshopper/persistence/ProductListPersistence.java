package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.ProductListVisitor;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;

import java.util.List;

public interface ProductListPersistence extends ProductListVisitor {
    public void addList(ProductList productList);

    public void updateList(ProductList productList);

    public void deleteList(ProductList productList);

    public List<ShoppingList> getExistingShoppingLists();

    public List<RequestList> getExistingRequestLists();

    public boolean listExists(ProductList productList);
}
