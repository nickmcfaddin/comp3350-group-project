package com.example.easyshopper.presentation.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyshopper.R;

public class HomeProductViewHolder extends RecyclerView.ViewHolder{
    TextView homeProductName, homeProductStockQuantity, homeProductDesiredQuantity;
    private ImageButton removeStockButton;
    private ImageButton addStockButton;
    private ImageButton removeDesiredButton;
    private ImageButton addDesiredButton;
    private int recyclerViewId;

    public HomeProductViewHolder(@NonNull View itemView, HomeProductButtonInterface listener, int recyclerViewId) {
        super(itemView);
        this.recyclerViewId = recyclerViewId;

        // set values for home products
        homeProductName = itemView.findViewById(R.id.homeProductName);
        homeProductStockQuantity = itemView.findViewById(R.id.homeProductStockQuantity);
        homeProductDesiredQuantity = itemView.findViewById(R.id.homeProductDesiredQuantity);

        // Initialize views for buttons
        removeStockButton = itemView.findViewById(R.id.removeStockQuantity);
        addStockButton = itemView.findViewById(R.id.addStockQuantity);
        removeDesiredButton = itemView.findViewById(R.id.removeDesiredQuantity);
        addDesiredButton = itemView.findViewById(R.id.addDesiredQuantity);

        // Set click listeners
        removeStockButton.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onButtonClick(v, position, "removeStock", this.recyclerViewId);
            }
        });

        addStockButton.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onButtonClick(v, position, "addStock", this.recyclerViewId);
            }
        });

        removeDesiredButton.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onButtonClick(v, position, "removeDesired", this.recyclerViewId);
            }
        });

        addDesiredButton.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onButtonClick(v, position, "addDesired", this.recyclerViewId);
            }
        });

        // Set click listener for product name TextView
        homeProductName.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onButtonClick(v, position, "productName", this.recyclerViewId);
            }
        });
    }
}
