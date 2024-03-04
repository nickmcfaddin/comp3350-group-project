package com.example.easyshopper.presentation.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
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
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.presentation.adapter.HomeProductHiddenAdapter;
import com.example.easyshopper.presentation.adapter.HomeProductStockAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class InventoryDialog {
    private Context context;
    private HomeInventoryHandler homeInventoryHandler;

    public InventoryDialog(Context context, HomeInventoryHandler homeInventoryHandler) {
        this.context = context;
        this.homeInventoryHandler = homeInventoryHandler;
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

        TextView homeProdLifeTime = dialogView.findViewById(R.id.home_product_life_time_info);
        homeProdLifeTime.setText("Life time of " + homeProduct.getProductName() + ": " + homeProduct.getLifeTimeDays() + " days");

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
}
