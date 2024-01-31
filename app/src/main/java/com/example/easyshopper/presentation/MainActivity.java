package com.example.easyshopper.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.easyshopper.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //id of currentFragment that is being displayed
    int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        //get and init components
        ShoppingListFragment shoppingListFragment = new ShoppingListFragment();
        SearchFragment searchFragment = new SearchFragment();
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

            //If the current fragment was the search page, retrieve the searched list to display once the user comes back
            if (currentFragment == bottomNavigationView.getMenu().getItem(2).getItemId()) {
                //busStopsList = searchFragment.getBusStops();
            }

            //display the fragment requested by the user
            if (itemId == R.id.shoppingList) {
                replaceFragment(shoppingListFragment);

                //Update Icons
                item.setIcon(R.drawable.icon_paper_fill);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.icon_home_line);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.icon_search_line);
            } else if (itemId == R.id.homeInventory) {
                replaceFragment(new HomeFragment());

                //Update Icons
                item.setIcon(R.drawable.icon_home_fill);
                bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.icon_paper_line);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.icon_search_line);
            } else if (itemId == R.id.search) {
                replaceFragment(searchFragment);

                //display the list that was previously searched by the user
                //searchFragment.setBusStops(busStopsList);

                //Update Icons
                item.setIcon(R.drawable.icon_search_fill);
                bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.icon_paper_line);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.icon_home_line);
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