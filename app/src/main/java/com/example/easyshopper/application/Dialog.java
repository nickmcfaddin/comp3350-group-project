package com.example.easyshopper.application;

import android.content.Context;

import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.UserHandler;
import com.example.easyshopper.presentation.dialog.InventoryDialog;
import com.example.easyshopper.presentation.dialog.ListDialog;
import com.example.easyshopper.presentation.dialog.RequestListDialog;

import java.io.Serializable;

public class Dialog implements Serializable {
    private static ListDialog listDialog;
    private static InventoryDialog inventoryDialog;
    private static RequestListDialog requestListDialog;

    public Dialog(Context context, UserHandler userHandler, RequestListHandler requestListHandler){
        requestListDialog = new RequestListDialog(context, null);
    }

    public static RequestListDialog getRequestListDialog() {
        return requestListDialog;
    }
}
