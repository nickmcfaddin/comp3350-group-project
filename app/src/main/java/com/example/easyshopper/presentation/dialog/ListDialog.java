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
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.presentation.adapter.DynamicListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListDialog {
    private Context context;
    private DynamicListAdapter dynamicListAdapter;

    public ListDialog(Context context, DynamicListAdapter dynamicListAdapter) {
        this.context = context;
        this.dynamicListAdapter = dynamicListAdapter;
    }

    //show a prompt asking the user what products they would like added to their lists
    public void chooseProductsDialog(List<Product> productList) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected products
        boolean[] checkedItems = new boolean[productList.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Products to Add:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<Product> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, productList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        // Set multi-choice items and listen for changes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedItems[position] = !checkedItems[position];
            checkedTextView.setChecked(checkedItems[position]);
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> clickedProducts = new ArrayList<>();

                //get list of clicked products
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedProducts.add(productList.get(i));
                    }
                }

                //check if user has not clicked any product
                if(!clickedProducts.isEmpty()){
                    //show prompt asking which shopping lists to add to
                    addProductsToListDialog(clickedProducts);
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

    //show a prompt asking the user what lists they would like to add their products onto
    public void addProductsToListDialog(List<Product> clickedProducts) {
        List<ShoppingList> shoppingLists = ShoppingListHandler.getAllShoppingLists();

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[shoppingLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Shopping Lists to Add Products Onto:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<ShoppingList> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, shoppingLists);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        // Set multi-choice items and listen for changes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedItems[position] = !checkedItems[position];
            checkedTextView.setChecked(checkedItems[position]);
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShoppingList> clickedShoppingLists = new ArrayList<>();

                //get list of clicked products
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedShoppingLists.add(shoppingLists.get(i));
                    }
                }

                //add clicked products to clicked shopping lists
                for(ShoppingList shoppingList : clickedShoppingLists) {
                    for(Product product : clickedProducts) {
                        ShoppingListHandler.addItemToList(product, shoppingList);
                    }
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
                ShoppingListHandler.createShoppingList(store);

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

    //show a prompt asking the user what list they'd like to delete
    public void deleteListDialog() {
        //get all existing shopping lists
        List<ShoppingList> shoppingLists = ShoppingListHandler.getAllShoppingLists();

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[shoppingLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Shopping Lists to Delete:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<ShoppingList> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, shoppingLists);
        listView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        // Set multi-choice items and listen for changes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedItems[position] = !checkedItems[position];
            checkedTextView.setChecked(checkedItems[position]);
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShoppingList> clickedShoppingLists = new ArrayList<>();

                //get list of clicked lists
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedShoppingLists.add(shoppingLists.get(i));
                    }
                }

                //remove shopping lists
                for(ShoppingList shoppingList : clickedShoppingLists) {
                    ShoppingListHandler.removeShoppingList(shoppingList);
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
