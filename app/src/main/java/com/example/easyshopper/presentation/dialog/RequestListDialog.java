package com.example.easyshopper.presentation.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.presentation.adapter.DynamicListAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestListDialog implements Serializable {
    private Context context;
    private DynamicListAdapter dynamicListAdapter;

    public RequestListDialog(Context context, DynamicListAdapter dynamicListAdapter) {
        this.context = context;
        this.dynamicListAdapter = dynamicListAdapter;
    }

    //create user and make a request list
    public void createListDialog() {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_user, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        EditText inputText = dialogView.findViewById(R.id.user_name_input);
        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.confirm_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = UserHandler.createUser(inputText.getText().toString());
                RequestListHandler.createRequestList(newUser);

                dynamicListAdapter.updateData();
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

    //show a prompt asking the user what products they would like added to their lists
    public void chooseProductsDialog(List<Product> productList, List<ProductList> productLists) {
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
                    addProductsToListDialog(clickedProducts, productLists);
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
    public void addProductsToListDialog(List<Product> clickedProducts, List<ProductList> productLists) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[productLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Shopping Lists to Add Products Onto:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<ProductList> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, productLists);
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
                List<ProductList> clickedProductList = new ArrayList<>();

                //get list of clicked products
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedProductList.add(productLists.get(i));
                    }
                }

                //add clicked products to clicked shopping lists
                for(ProductList productList : clickedProductList) {
                    for(Product product : clickedProducts) {
                        if(productList instanceof ShoppingList) {
                            ShoppingListHandler.addItemToList(product, (ShoppingList) productList);
                        } else {
                            RequestListHandler.addItemToList(product, (RequestList) productList);
                        }
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

    public void approveProductsDialog(RequestList requestList) {
        List<Product> cart = requestList.getCart();
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected products
        boolean[] checkedItems = new boolean[cart.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Products to Approve:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<Product> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, cart);
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
                        clickedProducts.add(cart.get(i));
                    }
                }

                //check if user has not clicked any product
                if(!clickedProducts.isEmpty()){
                    //show prompt asking which shopping lists to add to
                    shipProductsToShoppingList(requestList,clickedProducts);
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

    public void shipProductsToShoppingList(RequestList requestList, List<Product> clickedProducts) {
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

                if (clickedShoppingLists.isEmpty()) {
                    return;
                }

                //add clicked products to clicked shopping lists and remove from request list
                for(Product product : clickedProducts) {
                    for(ShoppingList shoppingList : clickedShoppingLists) {
                        ShoppingListHandler.addItemToList(product, shoppingList);
                    }
                    RequestListHandler.removeProduct(product, requestList);
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
    public void setDynamicListAdapter(DynamicListAdapter dynamicListAdapter) {
        this.dynamicListAdapter = dynamicListAdapter;
    }

    public void clearListDialog(RequestList requestList) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_delete_prompt, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Clear list?");

        Button yesButton = dialogView.findViewById(R.id.yes_btn);
        Button noButton = dialogView.findViewById(R.id.no_btn);

        //set behaviour for buttons
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove product from list and update view
                RequestListHandler.clearList(requestList);

                dynamicListAdapter.updateData();

                alertDialog.dismiss();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
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
        List<RequestList> requestLists = RequestListHandler.getAllRequestLists();

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[requestLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Shopping Lists to Delete:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<RequestList> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, requestLists);
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
                List<RequestList> clickedShoppingLists = new ArrayList<>();

                //get list of clicked lists
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedShoppingLists.add(requestLists.get(i));
                    }
                }

                //remove shopping lists
                for(RequestList requestList : clickedShoppingLists) {
                    RequestListHandler.deleteList(requestList);
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
