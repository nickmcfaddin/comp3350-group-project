package com.example.easyshopper.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.Store;

import java.util.HashMap;
import java.util.List;

public class ShoppingListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Store> shoppingListHeaders;
    private HashMap<Store, List<Product>> shoppingLists;

    public ShoppingListAdapter(Context context, List<Store> shoppingListHeaders, HashMap<Store, List<Product>> shoppingLists) {
        this.context = context;
        this.shoppingListHeaders = shoppingListHeaders;
        this.shoppingLists = shoppingLists;
    }

    @Override
    public int getGroupCount() {
        return shoppingListHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return shoppingLists.get(shoppingListHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.shoppingListHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return shoppingLists.get(shoppingListHeaders.get(groupPosition)).get(childPosition);
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
        Store shoppingList = (Store) getGroup(groupPosition);
        String shoppingListTitle = shoppingList.getStoreName();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shopping_list_header, null);
        }

        TextView shoppingListTitleView = convertView.findViewById(R.id.shopping_list_header_name);
        shoppingListTitleView.setText(shoppingListTitle);

        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Product shoppingListProduct = (Product) getChild(groupPosition,childPosition);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shopping_list_item, null);
        }

        TextView productNameView = convertView.findViewById(R.id.productNameView);
        TextView productPriceView = convertView.findViewById(R.id.productPriceView);

        //productNameView.setText(shoppingListProduct.getName());
        //productPriceView.setText(shoppingListProduct.getPrice());

        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
