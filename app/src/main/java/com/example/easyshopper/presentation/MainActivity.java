package com.example.easyshopper.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.easyshopper.R;
import com.example.easyshopper.application.Dialog;
import com.example.easyshopper.application.KeyStrings;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.persistence.utils.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //id of currentFragment that is being displayed
    int currentFragment;

    //get handlers
    private boolean forProduction = true;
    private ProductHandler productHandler;
    private StoreHandler storeHandler;
    private ShoppingListHandler shoppingListHandler;
    private HomeInventoryHandler homeInventoryHandler;
    private RequestListHandler requestListHandler;
    private UserHandler userHandler;
    private Dialog dialog;

    public void setForProduction(boolean forProduction) {
        this.forProduction = forProduction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.copyDatabaseToDevice(this);

        if (savedInstanceState != null) {
            productHandler = (ProductHandler) savedInstanceState.getSerializable(KeyStrings.PRODUCT_HANDLER_KEY);
            storeHandler = (StoreHandler) savedInstanceState.getSerializable(KeyStrings.STORE_HANDLER_KEY);
            shoppingListHandler = (ShoppingListHandler) savedInstanceState.getSerializable(KeyStrings.LIST_HANDLER_KEY);
            homeInventoryHandler = (HomeInventoryHandler) savedInstanceState.getSerializable(KeyStrings.HOME_INVENTORY_HANDLER_KEY);
            userHandler = (UserHandler) savedInstanceState.getSerializable(KeyStrings.USER_HANDLER_KEY);
            requestListHandler = (RequestListHandler) savedInstanceState.getSerializable(KeyStrings.REQUEST_HANDLER_KEY);
            dialog = (Dialog) savedInstanceState.getSerializable(KeyStrings.DIALOG_KEY);
        }
        else {
            productHandler = new ProductHandler(forProduction);
            storeHandler = new StoreHandler(forProduction);
            shoppingListHandler = new ShoppingListHandler(forProduction);
            homeInventoryHandler = new HomeInventoryHandler(forProduction);
            userHandler = new UserHandler(forProduction);
            requestListHandler = new RequestListHandler(forProduction);
            dialog = new Dialog(this);
        }

        initComponents();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KeyStrings.PRODUCT_HANDLER_KEY, productHandler);
        outState.putSerializable(KeyStrings.STORE_HANDLER_KEY, storeHandler);
        outState.putSerializable(KeyStrings.LIST_HANDLER_KEY, shoppingListHandler);
        outState.putSerializable(KeyStrings.HOME_INVENTORY_HANDLER_KEY, homeInventoryHandler);
        outState.putSerializable(KeyStrings.USER_HANDLER_KEY, userHandler);
        outState.putSerializable(KeyStrings.REQUEST_HANDLER_KEY, requestListHandler);
        outState.putSerializable(KeyStrings.DIALOG_KEY, dialog);
        super.onSaveInstanceState(outState);
    }

    private void initComponents() {
        //get and init components
        ShoppingListFragment shoppingListFragment = ShoppingListFragment.newInstance();
        SearchFragment searchFragment = SearchFragment.newInstance();
        InventoryFragment inventoryFragment = InventoryFragment.newInstance();
        UserRequestFragment userRequestFragment = UserRequestFragment.newInstance();
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
                updateBottomNavigationIcons(0);
            } else if (itemId == R.id.homeInventory) {
                // Home Inventory
                replaceFragment(inventoryFragment);

                //Update Icons
                updateBottomNavigationIcons(1);
            } else if (itemId == R.id.userRequest) {
                // User Request
                replaceFragment(userRequestFragment);

                //Update Icons
                updateBottomNavigationIcons(2);
            } else if (itemId == R.id.search) {
                // Search
                replaceFragment(searchFragment);

                // Update Icons
                updateBottomNavigationIcons(3);
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

    private void updateBottomNavigationIcons(int selectedItemIndex) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        MenuItem[] menuItems = {
                bottomNavigationView.getMenu().getItem(0),
                bottomNavigationView.getMenu().getItem(1),
                bottomNavigationView.getMenu().getItem(2),
                bottomNavigationView.getMenu().getItem(3)
        };

        int[] iconIdsLine = {
                R.drawable.icon_paper_line,
                R.drawable.icon_home_line,
                R.drawable.icon_request_line,
                R.drawable.icon_search_line
        };

        int[] iconIdsFill = {
                R.drawable.icon_paper_fill,
                R.drawable.icon_home_fill,
                R.drawable.icon_request_fill,
                R.drawable.icon_search_fill
        };

        for (int i = 0; i < menuItems.length; i++) {
            if (i == selectedItemIndex) {
                menuItems[i].setIcon(iconIdsFill[i]);
            } else {
                menuItems[i].setIcon(iconIdsLine[i]);
            }
        }
    }
}