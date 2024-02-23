package com.example.easyshopper.presentation.adapter;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyshopper.R;

public class HomeProductStockViewHolder extends RecyclerView.ViewHolder {
    TextView homeProductName, homeProductStockQuantity, homeProductDesiredQuantity;

    public HomeProductStockViewHolder(@NonNull View itemView) {
        super(itemView);
        homeProductName = itemView.findViewById(R.id.homeProductName);
        homeProductStockQuantity = itemView.findViewById(R.id.homeProductStockQuantity);
        homeProductDesiredQuantity = itemView.findViewById(R.id.homeProductDesiredQuantity);
    }
}
