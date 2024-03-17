package com.example.easyshopper.presentation.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.application.Dialog;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.logic.exceptions.InvalidShoppingListException;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.presentation.adapter.DynamicListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDialog extends ProductListDialog {
    public ShoppingListDialog(Context context) {
        super(context);
    }

    //show a prompt asking the user what products they would like added to their lists
    public void chooseProductsDialog() {
        chooseProductsDialog(new ArrayList<>(ShoppingListHandler.getAllShoppingLists()));
    }

    public void deleteListDialog() {
        deleteListDialog(new ArrayList<>(ShoppingListHandler.getAllShoppingLists()));
    }

    //show a prompt asking the user what store they'd like to make a shopping list for
    public void createListDialog() {
        final Store[] selectedStore = {null};

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_list, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        List<Store> storeList = StoreHandler.getExistingStores();
        AutoCompleteTextView autoCompleteTextView = dialogView.findViewById(R.id.dropdown_field);
        ArrayAdapter<Store> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, storeList);
        autoCompleteTextView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        //track what store user selected
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStore[0] = (Store) parent.getItemAtPosition(position);
            }
        });

        //set behaviour for buttons
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user has entered values for both inputs
                Store store = selectedStore[0];
                if(store == null)
                {
                    return;
                }

                //create shopping list and update the list view
                try {
                    ShoppingListHandler.createShoppingList(store);
                } catch (InvalidShoppingListException e) {
                    Dialog.showErrorMessageDialog(e);
                    return;
                }

                //update the displayed list
                if(dynamicListAdapter != null) {
                    dynamicListAdapter.updateData();
                }

                alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }
}
