package com.example.easyshopper.presentation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.HomeInventory;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.presentation.adapter.DynamicListAdapter;
import com.example.easyshopper.presentation.adapter.HomeProductHiddenAdapter;
import com.example.easyshopper.presentation.adapter.HomeProductStockAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InventoryDialog {
    private Context context;
    private HomeInventoryHandler homeInventoryHandler;
    private HomeProductStockAdapter homeProductStockAdapter;
    private HomeProductHiddenAdapter homeProductHiddenAdapter;

    public InventoryDialog(Context context, HomeInventoryHandler homeInventoryHandler, HomeProductStockAdapter homeProductStockAdapter, HomeProductHiddenAdapter homeProductHiddenAdapter) {
        this.context = context;
        this.homeInventoryHandler = homeInventoryHandler;
        this.homeProductStockAdapter = homeProductStockAdapter;
        this.homeProductHiddenAdapter = homeProductHiddenAdapter;
    }

    @SuppressLint("SetTextI18n")
    public void showHomeProductExpiryDate(HomeProduct homeProduct){
        //get all expiry date of products to show
        List<String> expiryDates = homeInventoryHandler.getHomeProductExpiryDates(homeProduct);
        List<String> expiryDateSortedAscending = homeInventoryHandler.getHomeProductSortedExpiryDatesAscending(homeProduct);
        List<String> expiryDateSortedDescending = homeInventoryHandler.getHomeProductSortedExpiryDatesDescending(homeProduct);

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_home_product_expiry_dates, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.home_product_expiry_dates_title);
        dialogTitle.setText("All Expiry Dates of " + homeProduct.getProductName() + ":");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, expiryDates);
        listView.setAdapter(listAdapter);

        Button closeBtn = dialogView.findViewById(R.id.close_btn);
        ImageButton sortAscending = dialogView.findViewById(R.id.sortExpiryDateAscendingBtn);
        ImageButton sortDescending = dialogView.findViewById(R.id.sortExpiryDateDescendingBtn);

        // on click methods
        closeBtn.setOnClickListener(v -> alertDialog.dismiss());

        sortAscending.setOnClickListener(v -> {
            ArrayAdapter<String> sortedListAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, expiryDateSortedAscending);
            listView.setAdapter(sortedListAdapter);
            sortedListAdapter.notifyDataSetChanged();
        });

        sortDescending.setOnClickListener(v -> {
            ArrayAdapter<String> sortedListAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, expiryDateSortedDescending);
            listView.setAdapter(sortedListAdapter);
            sortedListAdapter.notifyDataSetChanged();
        });

        // Show the dialog
        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void showHomeProductAddExpiryDate(HomeProduct homeProduct, DialogCallback callback){
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_home_product_add_expiry_date, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.add_expiry_date_title);
        dialogTitle.setText("Adding Stock Item To " + homeProduct.getProductName());

        Button cancelBtn = dialogView.findViewById(R.id.cancel_btn);
        Button confirmBtn = dialogView.findViewById(R.id.confirm_btn);
        EditText userInputEditText = dialogView.findViewById(R.id.add_expiry_date_input);
        TextView invalidInputWarning = dialogView.findViewById(R.id.add_expiry_date_invalid_message);

        // on click methods
        cancelBtn.setOnClickListener(v -> alertDialog.dismiss());

        confirmBtn.setOnClickListener(v -> {
            String userInput = userInputEditText.getText().toString();

            if (!addExpiryDate(userInput)){
                invalidInputWarning.setVisibility(View.VISIBLE);
            }
            else{
                invalidInputWarning.setVisibility(View.GONE);

                homeInventoryHandler.incrementStockQuantityBy1(homeProduct, userInput);
                homeProductStockAdapter.updateData(homeInventoryHandler.getStockProduct());
                homeProductHiddenAdapter.updateData(homeInventoryHandler.getHiddenProduct());

                callback.onDialogResult(userInput);

                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }

    // Interface for callback
    public interface DialogCallback {
        void onDialogResult(String userInput);
    }

    //

    public boolean addExpiryDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        // regular expression pattern for ISO 8601 date format (yyyy-MM-dd)
        String iso8601Pattern = "\\d{4}-\\d{2}-\\d{2}";

        // check if the date matches the ISO 8601 format
        if (Pattern.matches(iso8601Pattern, date)) {
            // If the date matches the format, attempt to parse it
            try {
                LocalDate parsedDate = LocalDate.parse(date, formatter);
                // If parsing is successful, add the date to list of expiry dates
                return true;
            } catch (Exception e) {
                // Handle any parsing exceptions here
                System.out.println("Error parsing date: " + e.getMessage());
            }
        } else {
            // If the date does not match the ISO 8601 format, inform the user
            System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
        }

        return false;
    }
}
