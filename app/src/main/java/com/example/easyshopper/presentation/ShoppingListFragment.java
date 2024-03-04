package com.example.easyshopper.presentation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.presentation.adapter.ShoppingListAdapter;
import com.example.easyshopper.presentation.dialog.ListDialog;

import java.util.List;

public class ShoppingListFragment extends Fragment {
    //keys for serializable objects
    private static final String PRODUCT_HANDLER_KEY = "productHandler";
    private static final String STORE_HANDLER_KEY = "storeHandler";
    private static final String LIST_HANDLER_KEY = "shoppingListHandler";

    private ListDialog listDialog;

    private StoreHandler storeHandler;
    private ProductHandler productHandler;
    private ShoppingListHandler shoppingListHandler;

    private List<ShoppingList> shoppingLists;
    private ShoppingListAdapter shoppingListAdapter;

    public ShoppingListFragment() {
    }

    public ShoppingListFragment(StoreHandler storeHandler, ProductHandler productHandler, ShoppingListHandler shoppingListHandler) {
        this.storeHandler = storeHandler;
        this.productHandler = productHandler;
        this.shoppingListHandler = shoppingListHandler;
    }

    public static ShoppingListFragment newInstance(ProductHandler productHandler, StoreHandler storeHandler, ShoppingListHandler shoppingListHandler) {
        Bundle args = new Bundle();
        ShoppingListFragment fragment = new ShoppingListFragment(storeHandler, productHandler, shoppingListHandler);
        args.putSerializable(PRODUCT_HANDLER_KEY, productHandler);
        args.putSerializable(STORE_HANDLER_KEY, storeHandler);
        args.putSerializable(LIST_HANDLER_KEY, shoppingListHandler);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            productHandler = (ProductHandler) savedInstanceState.getSerializable(PRODUCT_HANDLER_KEY);
            storeHandler = (StoreHandler) savedInstanceState.getSerializable(STORE_HANDLER_KEY);
            shoppingListHandler = (ShoppingListHandler) savedInstanceState.getSerializable(LIST_HANDLER_KEY);
        } else if (getArguments() != null) {
            productHandler = (ProductHandler) getArguments().getSerializable(PRODUCT_HANDLER_KEY);
            storeHandler = (StoreHandler) getArguments().getSerializable(STORE_HANDLER_KEY);
            shoppingListHandler = (ShoppingListHandler) getArguments().getSerializable(LIST_HANDLER_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PRODUCT_HANDLER_KEY, productHandler);
        outState.putSerializable(STORE_HANDLER_KEY, storeHandler);
        outState.putSerializable(LIST_HANDLER_KEY, shoppingListHandler);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_shopping_list, container, false);

        //set behaviour for components
        initComponents(rootView);

        return rootView;
    }

    private void initComponents(View rootView) {
        //set adapters for list view
        shoppingLists = shoppingListHandler.getAllShoppingLists();
        ExpandableListView shoppingListView = rootView.findViewById(R.id.shoppingListView);
        shoppingListAdapter = new ShoppingListAdapter(getContext(), shoppingLists, productHandler, shoppingListHandler);
        shoppingListView.setAdapter(shoppingListAdapter);

        //allow for dialogs to be displayed in this class
        listDialog = new ListDialog(getContext(), shoppingListHandler,storeHandler,shoppingListAdapter);

        //set behaviour for button
        ImageButton addButton = rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(getContext(), R.style.MyMenuStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, v);
                popupMenu.getMenuInflater().inflate(R.menu.shopping_list_add_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.addProduct) {
                            listDialog.chooseProductsDialog(productHandler.getAllProducts());
                        } else if (menuItem.getItemId() == R.id.createList) {
                            listDialog.createListDialog();
                        } else if (menuItem.getItemId() == R.id.deleteList) {
                            listDialog.deleteListDialog();
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}