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
import com.example.easyshopper.application.Dialog;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.presentation.adapter.ShoppingListAdapter;
import com.example.easyshopper.presentation.dialog.ShoppingListDialog;
import java.util.List;

public class ShoppingListFragment extends Fragment {
    //keys for serializable objects
    private ShoppingListDialog shoppingListDialog;
    private List<ShoppingList> shoppingLists;
    private ShoppingListAdapter shoppingListAdapter;

    public ShoppingListFragment() {
    }

    public static ShoppingListFragment newInstance() {
        return new ShoppingListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        //set behaviour for components
        initComponents(rootView);

        return rootView;
    }

    private void initComponents(View rootView) {
        //set adapters for list view
        shoppingLists = ShoppingListHandler.getAllShoppingLists();
        ExpandableListView shoppingListView = rootView.findViewById(R.id.shoppingListView);
        shoppingListAdapter = new ShoppingListAdapter(getContext(), shoppingLists);
        shoppingListView.setAdapter(shoppingListAdapter);

        //allow for dialogs to be displayed in this class
        shoppingListDialog = Dialog.getShoppingListDialog();
        shoppingListDialog.setDynamicListAdapter(shoppingListAdapter);

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
                            shoppingListDialog.chooseProductsDialog();
                        } else if (menuItem.getItemId() == R.id.createList) {
                            shoppingListDialog.createListDialog();
                        } else if (menuItem.getItemId() == R.id.deleteList) {
                            shoppingListDialog.deleteListDialog();
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        ImageButton exportButton = rootView.findViewById(R.id.exportButton);

        exportButton.setOnClickListener(v -> shoppingListDialog.exportListDialog(getContext()));
    }
}