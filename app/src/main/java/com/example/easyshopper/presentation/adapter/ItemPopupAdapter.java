package com.example.easyshopper.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Store;

import java.util.List;

public class ItemPopupAdapter extends RecyclerView.Adapter<ItemPopupAdapter.ProductPriceViewHolder> {
    private Context context;
    private List<Price> priceList;

    public ItemPopupAdapter(Context context, List<Price> priceList) {
        this.context = context;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public ProductPriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductPriceViewHolder(LayoutInflater.from(context).inflate(R.layout.product_price_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductPriceViewHolder holder, int position) {
        //get values
        Price currentPrice = priceList.get(position);

        int storeID = currentPrice.getStoreID();
        Store store = StoreHandler.getStoreById(storeID);

        //bind values to the display
        holder.productPriceView.setText(currentPrice.getPriceFormatted());
        holder.productStoreNameView.setText(store.getStoreName());
    }

    @Override
    public int getItemCount() {
        return priceList == null ? 0 : priceList.size();
    }

    public static class ProductPriceViewHolder extends RecyclerView.ViewHolder {
        TextView productStoreNameView;
        TextView productPriceView;

        public ProductPriceViewHolder(@NonNull View itemView) {
            super(itemView);
            productStoreNameView = itemView.findViewById(R.id.productStoreView);
            productPriceView = itemView.findViewById(R.id.productPriceView);
        }
    }

}
