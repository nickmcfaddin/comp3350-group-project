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

public class HomeProductHiddenAdapter extends RecyclerView.Adapter<HomeProductStockViewHolder> {
    @NonNull
    private Context context;
    private List<HomeProduct> hiddenHomeProduct;
    private HomeInventoryHandler homeInventoryHandler;

    public HomeProductHiddenAdapter(@NonNull Context context, List<HomeProduct> hiddenHomeProduct, HomeInventoryHandler homeInventoryHandler) {
        this.context = context;
        this.hiddenHomeProduct = hiddenHomeProduct;
        this.homeInventoryHandler = homeInventoryHandler;
    }

    @NonNull
    public HomeProductStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeProductStockViewHolder(LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductStockViewHolder holder, int position) {
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
