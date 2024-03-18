package com.example.easyshopper.application;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.logic.exceptions.LogicException;
import com.example.easyshopper.presentation.dialog.InventoryDialog;
import com.example.easyshopper.presentation.dialog.ShoppingListDialog;
import com.example.easyshopper.presentation.dialog.RequestListDialog;

import java.io.Serializable;

public class Dialog implements Serializable {
    private static Context context;
    private static ShoppingListDialog shoppingListDialog;
    private static InventoryDialog inventoryDialog;
    private static RequestListDialog requestListDialog;

    public Dialog(Context context){
        Dialog.context = context;
        requestListDialog = new RequestListDialog(context);
        inventoryDialog = new InventoryDialog(context);
        shoppingListDialog = new ShoppingListDialog(context);
    }

    public static RequestListDialog getRequestListDialog() {
        return requestListDialog;
    }

    public static ShoppingListDialog getShoppingListDialog() {
        return shoppingListDialog;
    }

    public static InventoryDialog getInventoryDialog() {
        return inventoryDialog;
    }

    public static void showErrorMessageDialog(LogicException le) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_error_message, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.error_message_view);
        dialogTitle.setText(le.getMessage());

        Button okButton = dialogView.findViewById(R.id.ok_btn);

        //set behaviour for buttons
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }

}
