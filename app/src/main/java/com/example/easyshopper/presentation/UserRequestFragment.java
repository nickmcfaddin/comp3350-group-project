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
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.presentation.adapter.RequestListAdapter;
import com.example.easyshopper.presentation.dialog.ListDialog;
import com.example.easyshopper.presentation.dialog.RequestListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRequestFragment extends Fragment {
    private RequestListDialog requestListDialog;

    public UserRequestFragment() {
        // Required empty public constructor
    }

    public static UserRequestFragment newInstance() {
        return new UserRequestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_user_request, container, false);

        //set behaviour for components
        initComponents(rootView);

        return rootView;
    }

    private void initComponents(View rootView) {
        //set adapters for list view
        List<RequestList> requestLists = RequestListHandler.getAllRequestLists();
        ExpandableListView requestListView = rootView.findViewById(R.id.requestListView);
        RequestListAdapter requestListAdapter = new RequestListAdapter(getContext(), requestLists);
        requestListView.setAdapter(requestListAdapter);

        //allow for dialogs to be displayed in this class
        requestListDialog = Dialog.getRequestListDialog();
        requestListDialog.setDynamicListAdapter(requestListAdapter);

        //set behaviour for button
        ImageButton addButton = rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(getContext(), R.style.MyMenuStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, v);
                popupMenu.getMenuInflater().inflate(R.menu.request_list_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.addUser) {
                            requestListDialog.createListDialog();
                        } else if (menuItem.getItemId() == R.id.deleteUser) {
                            requestListDialog.deleteListDialog();
                        } else if (menuItem.getItemId() == R.id.addProductsToRequest) {
                            requestListDialog.chooseProductsDialog(ProductHandler.getAllProducts(), new ArrayList<>(RequestListHandler.getAllRequestLists()));
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}