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
import com.example.easyshopper.objects.RequestList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRequestFragment extends Fragment {
    private ListDialog listDialog;

    private List<RequestList> requestLists;
    private RequestList requestListAdapter;

    public UserRequestFragment() {
        // Required empty public constructor
    }

    public static UserRequestFragment newInstance(String param1, String param2) {
        UserRequestFragment fragment = new UserRequestFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
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
        //requestLists = requestListHandler.getAllRequestLists();
        ExpandableListView requestListView = rootView.findViewById(R.id.requestListView);
        //requestListAdapter = new ShoppingListAdapter(getContext(), shoppingLists, productHandler, shoppingListHandler);
        //requestListView.setAdapter(requestListAdapter);

        //allow for dialogs to be displayed in this class
        //dialog = new Dialog(getContext(), shoppingListHandler,storeHandler,shoppingListAdapter);

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

                        } else if (menuItem.getItemId() == R.id.createList) {

                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}