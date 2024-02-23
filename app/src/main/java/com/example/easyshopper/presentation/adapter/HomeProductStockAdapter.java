package com.example.easyshopper.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
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

    public HomeProductStockAdapter(@NonNull Context context, List<HomeProduct> homeProduct, HomeInventoryHandler homeInventoryHandler) {
        this.context = context;
        this.stockHomeProduct = homeProduct;
        this.homeInventoryHandler = homeInventoryHandler;
    }

    @NonNull
    public HomeProductStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeProductStockViewHolder(LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false));
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
