package com.example.easyshopper.presentation.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.application.Dialog;
import com.example.easyshopper.logic.RequestListHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ProductList;
import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import java.util.ArrayList;
import java.util.List;

public class RequestListAdapter extends BaseExpandableListAdapter implements DynamicListAdapter  {
    private Context context;
    private List<RequestList> requestLists;

    public RequestListAdapter(Context context, List<RequestList> requestLists) {
        this.context = context;
        this.requestLists = requestLists;
    }

    //update from internal sources
    public void updateData() {
        requestLists = RequestListHandler.getAllRequestLists();
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return requestLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return requestLists.get(groupPosition).getCart().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return requestLists.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return requestLists.get(groupPosition).getCart().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //get values
        final RequestList requestList = (RequestList) getGroup(groupPosition);
        String requestListTitle = requestList.getListName();

        //create view if not initialized
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.request_list_header, null);
        }

        //bind value to display
        TextView shoppingListTitleView = convertView.findViewById(R.id.request_list_header_name);
        ImageButton clearButton = convertView.findViewById(R.id.clearRequest);
        ImageButton approveButton = convertView.findViewById(R.id.approveRequest);

        clearButton.setFocusable(false);
        approveButton.setFocusable(false);

        shoppingListTitleView.setText(requestListTitle);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.getRequestListDialog().clearListDialog(requestList);
            }
        });
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.getRequestListDialog().approveProductsDialog(requestList);
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //get values
        final RequestList requestList = (RequestList) getGroup(groupPosition);
        final Product product = (Product) getChild(groupPosition,childPosition);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.request_list_item, null);
        }

        //bind values to display
        final TextView productNameView = convertView.findViewById(R.id.productNameView);

        productNameView.setText(product.getProductName());

        //ask user if they want to remove this item from the list
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeProductPrompt(requestList,product);
                return true;
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //show user a prompt asking them if they want to remove product from a list
    public void removeProductPrompt(RequestList requestList, Product product) {
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
                RequestListHandler.removeProduct(product,requestList);

                updateData();

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
}