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

public class HomeProductHiddenAdapter extends RecyclerView.Adapter<HomeProductViewHolder> {
    @NonNull
    private Context context;
    private List<HomeProduct> hiddenHomeProduct;
    private HomeProductButtonInterface buttonClickListener;
    private int recyclerViewId;

    public HomeProductHiddenAdapter(@NonNull Context context, List<HomeProduct> hiddenHomeProduct, HomeProductButtonInterface buttonClickListener, int recyclerViewId) {
        this.context = context;
        this.hiddenHomeProduct = hiddenHomeProduct;
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
        holder.homeProductName.setText(hiddenHomeProduct.get(position).getProductName());
        holder.homeProductStockQuantity.setText(String.valueOf(hiddenHomeProduct.get(position).getHomeProductStockQuantity()));
        holder.homeProductDesiredQuantity.setText(String.valueOf(hiddenHomeProduct.get(position).getHomeProductDesiredQuantity()));
    }

    @Override
    public int getItemCount() {
        return hiddenHomeProduct.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<HomeProduct> newHomeProductList) {
        hiddenHomeProduct.clear();
        hiddenHomeProduct.addAll(newHomeProductList);
        notifyDataSetChanged();
    }
}
