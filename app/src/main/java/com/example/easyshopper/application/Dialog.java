package com.example.easyshopper.application;

import android.content.Context;

import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.presentation.dialog.InventoryDialog;
import com.example.easyshopper.presentation.dialog.ShoppingListDialog;
import com.example.easyshopper.presentation.dialog.RequestListDialog;

import java.io.Serializable;

public class Dialog implements Serializable {
    private static ShoppingListDialog shoppingListDialog;
    private static InventoryDialog inventoryDialog;
    private static RequestListDialog requestListDialog;

    public Dialog(Context context){
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
}
