package com.example.easyshopper.presentation.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.application.Dialog;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;

import java.util.List;

public class ShoppingListAdapter extends BaseExpandableListAdapter implements DynamicListAdapter  {
    private Context context;
    private List<ShoppingList> shoppingLists;
    public ShoppingListAdapter(Context context, List<ShoppingList> shoppingLists) {
        this.context = context;
        this.shoppingLists = shoppingLists;
    }

    //update from internal sources
    public void updateData() {
        shoppingLists = ShoppingListHandler.getAllShoppingLists();
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

    /**
     * Indicates whether this adapter has stable unique IDs for the items.
     *
     * @return True if the adapter has stable IDs, false otherwise.
     *         Returning false indicates that the adapter does not guarantee
     *         that the IDs assigned to the items remain consistent across
     *         changes in the underlying data or ordering of the items.
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //get values
        final ShoppingList shoppingList = (ShoppingList) getGroup(groupPosition);
        String shoppingListTitle = shoppingList.getListName();

        //create view if not initialized
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shopping_list_header, null);
        }

        //bind value to display
        TextView shoppingListTitleView = convertView.findViewById(R.id.shopping_list_header_name);
        TextView shoppingListPriceView = convertView.findViewById(R.id.shopping_list_price);

        shoppingListTitleView.setText(shoppingListTitle);
        shoppingListPriceView.setText("$" + String.format("%.2f", ShoppingListHandler.getCartTotal(shoppingList)));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //get values
        final ShoppingList shoppingList = (ShoppingList) getGroup(groupPosition);
        final Product product = (Product) getChild(groupPosition,childPosition);
        final Store store = shoppingList.getStore();
        String price = "$" + String.format("%.2f", ProductHandler.getPriceOfProductInStore(product, store));

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shopping_list_item, null);
        }

        //bind values to display
        final TextView productNameView = convertView.findViewById(R.id.productNameView);
        final TextView productPriceView = convertView.findViewById(R.id.productPriceView);

        productNameView.setText(product.getProductName());
        productPriceView.setText(price);

        //ask user if they want to remove this item from the list
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog.getShoppingListDialog().removeProductPrompt(shoppingList,product);
                return true;
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
