package com.example.easyshopper.presentation.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import java.util.List;

public class ShoppingListAdapter extends BaseExpandableListAdapter  {
    private Context context;
    private List<ShoppingList> shoppingLists;
    private ProductHandler productHandler = new ProductHandler();
    private ShoppingListHandler shoppingListHandler = new ShoppingListHandler();

    public ShoppingListAdapter(Context context, List<ShoppingList> shoppingLists) {
        this.context = context;
        this.shoppingLists = shoppingLists;
    }

    //update from an outer sources
    public void updateData(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
        notifyDataSetChanged();
    }

    //update from internal sources
    public void updateData() {
        shoppingLists = shoppingListHandler.getAllShoppingLists();
        Log.i("asdas", shoppingLists.toString());
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return shoppingLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return shoppingLists.get(groupPosition).getCart().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return shoppingLists.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return shoppingLists.get(groupPosition).getCart().get(childPosition);
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
        final ShoppingList shoppingList = (ShoppingList) getGroup(groupPosition);
        String shoppingListTitle = shoppingList.getShoppingListName();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shopping_list_header, null);
        }

        TextView shoppingListTitleView = convertView.findViewById(R.id.shopping_list_header_name);
        TextView shoppingListPriceView = convertView.findViewById(R.id.shopping_list_price);

        shoppingListTitleView.setText(shoppingListTitle);
        shoppingListPriceView.setText("$" + shoppingList.cartTotal());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ShoppingList shoppingList = (ShoppingList) getGroup(groupPosition);
        final Product product = (Product) getChild(groupPosition,childPosition);
        final Store store = shoppingList.getStore();
        final boolean[] isStrikeThrough = {false};

        String price = "$" + productHandler.getPriceOfProductInStore(product, store);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shopping_list_item, null);
        }

        final TextView productNameView = convertView.findViewById(R.id.productNameView);
        final TextView productPriceView = convertView.findViewById(R.id.productPriceView);

        productNameView.setText(product.getProductName());
        productPriceView.setText(price);

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeProductPrompt(shoppingList,product);
                return true;
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void removeProductPrompt(ShoppingList shoppingList, Product product) {
        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_delete_prompt, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        dialogTitle.setText("Remove Product From List?");

        Button yesButton = dialogView.findViewById(R.id.yes_btn);
        Button noButton = dialogView.findViewById(R.id.no_btn);

        //set behaviour for buttons
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove product from list and update view
                shoppingListHandler.removeProduct(product,shoppingList);

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
