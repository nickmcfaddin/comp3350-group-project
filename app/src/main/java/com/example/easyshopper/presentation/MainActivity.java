package com.example.easyshopper.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.persistence.utils.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //keys for serializable objects
    private static final String PRODUCT_HANDLER_KEY = "productHandler";
    private static final String STORE_HANDLER_KEY = "storeHandler";
    private static final String LIST_HANDLER_KEY = "shoppingListHandler";

    //id of currentFragment that is being displayed
    int currentFragment;

    //get handlers
    private boolean forProduction = true;
    private ProductHandler productHandler;
    private StoreHandler storeHandler;
    private ShoppingListHandler shoppingListHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.copyDatabaseToDevice(this);

        if (savedInstanceState != null) {
            productHandler = (ProductHandler) savedInstanceState.getSerializable(PRODUCT_HANDLER_KEY);
            storeHandler = (StoreHandler) savedInstanceState.getSerializable(STORE_HANDLER_KEY);
            shoppingListHandler = (ShoppingListHandler) savedInstanceState.getSerializable(LIST_HANDLER_KEY);
        }
        else {
            productHandler = new ProductHandler(forProduction);
            storeHandler = new StoreHandler(forProduction);
            shoppingListHandler = new ShoppingListHandler(forProduction);
        }

        initComponents();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PRODUCT_HANDLER_KEY, productHandler);
        outState.putSerializable(STORE_HANDLER_KEY, storeHandler);
        outState.putSerializable(LIST_HANDLER_KEY, shoppingListHandler);
        super.onSaveInstanceState(outState);
    }

    private void initComponents() {
        //get and init components
        ShoppingListFragment shoppingListFragment = ShoppingListFragment.newInstance(productHandler, storeHandler, shoppingListHandler);
        SearchFragment searchFragment = SearchFragment.newInstance(productHandler, storeHandler);
        InventoryFragment inventoryFragment = new InventoryFragment();
        UserRequestFragment userRequestFragment = new UserRequestFragment();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //On startup, the Shopping List fragment is displayed
        replaceFragment(shoppingListFragment);
        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.icon_paper_fill);

        //Once a user has chosen to move to another page from the bottom navigation view, display the appropriate fragment
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            //Do not do anything if user has clicked to display the fragment already displayed
            if (itemId == currentFragment) {
                return false;
            }

            currentFragment = itemId;

            //display the fragment requested by the user
            if (itemId == R.id.shoppingList) {
                // Shopping List
                replaceFragment(shoppingListFragment);

                //Update Icons
                item.setIcon(R.drawable.icon_paper_fill);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.icon_home_line);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.icon_request_line);
                bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.icon_search_line);
            } else if (itemId == R.id.homeInventory) {
                // Home Inventory
                replaceFragment(inventoryFragment);

                //Update Icons
                bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.icon_paper_line);
                item.setIcon(R.drawable.icon_home_fill);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.icon_request_line);
                bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.icon_search_line);
            } else if (itemId == R.id.userRequest) {
                // User Request
                replaceFragment(userRequestFragment);

                //Update Icons
                bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.icon_paper_line);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.icon_home_line);
                item.setIcon(R.drawable.icon_request_fill);
                bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.icon_search_line);
            } else if (itemId == R.id.search) {
                // Search
                replaceFragment(searchFragment);

                // Update Icons
                bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.icon_paper_line);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.icon_home_line);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.icon_request_line);
                item.setIcon(R.drawable.icon_search_fill);
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}