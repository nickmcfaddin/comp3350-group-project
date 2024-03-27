package com.example.easyshopper.presentation.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ProductListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.presentation.adapter.DynamicListAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductListDialog {
    protected Context context;
    protected DynamicListAdapter dynamicListAdapter = null;

    public ProductListDialog(Context context) {
        this.context = context;
    }

    public void setDynamicListAdapter(DynamicListAdapter dynamicListAdapter) {
        this.dynamicListAdapter = dynamicListAdapter;
    }

    //show a prompt asking the user what products they would like added to their lists
    protected void chooseProductsDialog(List<ProductList> productLists) {
        List<Product> productList = ProductHandler.getAllProducts();

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
                    addProductsToListDialog(clickedProducts,productLists);
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
        dialogTitle.setText("Choose Lists to Add Products Onto:");

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
                List<ProductList> clickedLists = new ArrayList<>();

                //get list of clicked products
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedLists.add(productLists.get(i));
                    }
                }

                //add clicked products to clicked shopping lists
                for(ProductList productList : clickedLists) {
                    for(Product product : clickedProducts) {
                        ProductListHandler.addProductToCart(product, productList);
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

    //show a prompt asking the user what list they'd like to delete
    public void deleteListDialog(List<ProductList> productLists) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[productLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Lists to Delete:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<ProductList> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, productLists);
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
                List<ProductList> clickedLists = new ArrayList<>();

                //get list of clicked lists
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedLists.add(productLists.get(i));
                    }
                }

                //remove shopping lists
                for(ProductList productList : clickedLists) {
                    ProductListHandler.deleteList(productList);
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

    //show user a prompt asking them if they want to remove product from a list
    public void removeProductPrompt(ProductList productList, Product product) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_delete_prompt, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Remove Product From List?");

        Button yesButton = dialogView.findViewById(R.id.yes_btn);
        Button noButton = dialogView.findViewById(R.id.no_btn);

        //set behaviour for buttons
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove product from list and update view
                ProductListHandler.removeProductFromCart(product,productList);

                //update the displayed list
                if(dynamicListAdapter != null) {
                    dynamicListAdapter.updateData();
                }

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

    public abstract void createListDialog();
}
