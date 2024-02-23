package com.example.easyshopper.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.objects.HomeProduct;

import java.util.List;

public class HomeProductStockAdapter extends RecyclerView.Adapter<HomeProductStockViewHolder> {
    @NonNull
    private Context context;
    private List<HomeProduct> stockHomeProduct;
    private HomeInventoryHandler homeInventoryHandler;
    private HomeProductButtonInterface buttonClickListener;

    public HomeProductStockAdapter(@NonNull Context context, List<HomeProduct> homeProduct, HomeInventoryHandler homeInventoryHandler, HomeProductButtonInterface buttonClickListener) {
        this.context = context;
        this.stockHomeProduct = homeProduct;
        this.homeInventoryHandler = homeInventoryHandler;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    public HomeProductStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false);
        return new HomeProductStockViewHolder(itemView, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductStockViewHolder holder, int position) {
        holder.homeProductName.setText(stockHomeProduct.get(position).getProductName());
        holder.homeProductStockQuantity.setText(String.valueOf(stockHomeProduct.get(position).getHomeProductStockQuantity()));
        holder.homeProductDesiredQuantity.setText(String.valueOf(stockHomeProduct.get(position).getHomeProductDesiredQuantity()));
    }

    @Override
    public int getItemCount() {
        return stockHomeProduct.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<HomeProduct> newHomeProductList) {
        stockHomeProduct.clear();
        stockHomeProduct.addAll(newHomeProductList);
        notifyDataSetChanged();
    }
}
