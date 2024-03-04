package com.example.easyshopper.utils;

import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import java.util.List;

public class TestUtils {
    private ProductHandler productHandler;
    private ShoppingListHandler shoppingListHandler;
    private StoreHandler storeHandler;
    private HomeInventoryHandler homeInventoryHandler;

    public TestUtils(boolean forProduction){
        productHandler = new ProductHandler(forProduction);
        shoppingListHandler = new ShoppingListHandler(forProduction);
        storeHandler = new StoreHandler(forProduction);
        homeInventoryHandler = new HomeInventoryHandler(forProduction);
    }

    //ProductHandler implementations
    public List<Product> getAllProducts()
    {
        return productHandler.getAllProducts();
    }

    public Product getProductByID(int id)
    {
        return productHandler.getProductByID(id);
    }

    public double getPriceOfProductInStore(Product product, Store store) {return productHandler.getPriceOfProductInStore(product, store);}

    public List<Price> allStoreSortedPrice(Product product){return productHandler.allStoreSortedPrice(product);}


    //ShoppingListHandler implementations
    public List<ShoppingList> getAllShoppingList(){return shoppingListHandler.getAllShoppingLists();};

    public double getCartTotal(ShoppingList shoppingList){return shoppingListHandler.getCartTotal(shoppingList);};

    //StoreHandler implementations
    public Store getStoreById(int storeID)
    {
        return storeHandler.getStoreById(storeID);
    };

    public List<Store> getExistingStores(){ return storeHandler.getExistingStores();};

    // HomeInventoryHandler implementations
    public List<HomeProduct> getStockProduct() {return homeInventoryHandler.getStockProduct();}

    public List<HomeProduct> getSortedStockProduct() {return homeInventoryHandler.getSortedStockProduct();}

    public List<HomeProduct> getHiddenProduct() {return homeInventoryHandler.getHiddenProduct();}

    public List<String> getHomeProductExpiryDates(HomeProduct homeProduct){return homeInventoryHandler.getHomeProductExpiryDates(homeProduct);};

    public List<String> getHomeProductSortedExpiryDatesAscending(HomeProduct homeProduct){return homeInventoryHandler.getHomeProductSortedExpiryDatesAscending(homeProduct);};

    public List<String> getHomeProductSortedExpiryDatesDescending(HomeProduct homeProduct){return homeInventoryHandler.getHomeProductSortedExpiryDatesDescending(homeProduct);};
}
