package com.example.easyshopper.persistence;

import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import java.util.List;

//Implementations are all currently found in ShoppingListPersistenceStub
public interface ShoppingListPersistence {

    //Returns all currently existing ShoppingList's
    List<ShoppingList> getExistingShoppingLists();

    boolean listWithStoreExists(Store queryStore);

    //Currently removed functions -> ProductPersistence
    //Product addProduct(Product newProduct);
    //Product deleteProduct(Product product);
    //Product deleteProductByID(int productID);
}
