package com.example.easyshopper.utils;

import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.objects.User;

import java.util.List;

public class TestUtils {
    private ProductHandler productHandler;
    private ShoppingListHandler shoppingListHandler;
    private StoreHandler storeHandler;
    private HomeInventoryHandler homeInventoryHandler;
    private RequestListHandler requestListHandler;
    private UserHandler userHandler;

    public TestUtils(boolean forProduction){
        productHandler = new ProductHandler(forProduction);
        shoppingListHandler = new ShoppingListHandler(forProduction);
        storeHandler = new StoreHandler(forProduction);
        homeInventoryHandler = new HomeInventoryHandler(forProduction);
        requestListHandler = new RequestListHandler(forProduction);
        userHandler = new UserHandler(forProduction);
    }

    //ProductHandler implementations
    public List<Product> getAllProducts()
    {
        return ProductHandler.getAllProducts();
    }

    public Product getProductByID(int id)
    {
        return ProductHandler.getProductByID(id);
    }

    public double getPriceOfProductInStore(Product product, Store store) {return ProductHandler.getPriceOfProductInStore(product, store);}

    public List<Price> allStoreSortedPrice(Product product){return ProductHandler.allStoreSortedPrice(product);}


    //ShoppingListHandler implementations
    public List<ShoppingList> getAllShoppingList(){return ShoppingListHandler.getAllShoppingLists();};

    public double getCartTotal(ShoppingList shoppingList){return ShoppingListHandler.getCartTotal(shoppingList);};

    //StoreHandler implementations
    public Store getStoreById(int storeID)
    {
        return StoreHandler.getStoreById(storeID);
    };

    public List<Store> getExistingStores(){ return StoreHandler.getExistingStores();};

    // HomeInventoryHandler implementations
    public List<HomeProduct> getStockProduct() {return HomeInventoryHandler.getStockProduct();}

    public List<HomeProduct> getSortedStockProduct() {return HomeInventoryHandler.getSortedStockProduct();}

    public List<HomeProduct> getHiddenProduct() {return HomeInventoryHandler.getHiddenProduct();}

    public List<String> getHomeProductExpiryDates(HomeProduct homeProduct){return HomeInventoryHandler.getHomeProductExpiryDates(homeProduct);};

    public List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct){return HomeInventoryHandler.getHomeProductSortedExpiryDatesAscending(homeProduct);};

    public List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct){return HomeInventoryHandler.getHomeProductSortedExpiryDatesDescending(homeProduct);};

    // RequestListHandler implementations
    public List<RequestList> getAllRequestLists() {return RequestListHandler.getAllRequestLists();};

    // UserHandler implementations
    public List<User> getExistingUsers() {return UserHandler.getExistingUsers();};
}
