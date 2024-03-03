package com.example.easyshopper.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyshopper.R;
import com.example.easyshopper.objects.HomeProduct;

import java.util.List;

public class HomeProductStockAdapter extends RecyclerView.Adapter<HomeProductViewHolder> {
    @NonNull
    private Context context;
    private List<HomeProduct> stockHomeProduct;
    private HomeProductButtonInterface buttonClickListener;
    private int recyclerViewId;

    public HomeProductStockAdapter(@NonNull Context context, List<HomeProduct> homeProduct, HomeProductButtonInterface buttonClickListener, int recyclerViewId) {
        this.context = context;
        this.stockHomeProduct = homeProduct;
        this.buttonClickListener = buttonClickListener;
        this.recyclerViewId = recyclerViewId;
    }

    @NonNull
    public HomeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false);
        return new HomeProductViewHolder(itemView, buttonClickListener, recyclerViewId);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductViewHolder holder, int position) {
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
