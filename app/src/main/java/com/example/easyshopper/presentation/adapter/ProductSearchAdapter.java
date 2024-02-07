package com.example.easyshopper.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyshopper.R;
import com.example.easyshopper.objects.Product;

import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ProductSearchViewHolder> {
    private final ProductViewInterface productViewInterface;
    private Context context;
    private List<Product> productList;

    public ProductSearchAdapter(Context context, List<Product> productList, ProductViewInterface productViewInterface) {
        this.context = context;
        this.productList = productList;
        this.productViewInterface = productViewInterface;
    }

    @NonNull
    @Override
    public ProductSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.product_search_view, parent, false), productViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSearchViewHolder holder, int position) {
        Product currentProduct = productList.get(position);

        holder.productNameView.setText(currentProduct.getProductName());
    }

    @Override
    public int getItemCount() {
        if (productList != null)
            return productList.size();
        else {
            return 0;
        }
    }

    public void updateData(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public static class ProductSearchViewHolder extends RecyclerView.ViewHolder {
        TextView productNameView;

        public ProductSearchViewHolder(@NonNull View itemView, ProductViewInterface productViewInterface) {
            super(itemView);
            productNameView = itemView.findViewById(R.id.productNameView);

            //listen for item clicks and create a method
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(productViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            productViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
