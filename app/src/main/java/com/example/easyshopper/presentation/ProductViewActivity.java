package com.example.easyshopper.presentation;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.presentation.adapter.ItemPopupAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductViewActivity extends AppCompatActivity {
    ProductHandler productHandler = new ProductHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        // back button on-click
        ImageView backBtn = findViewById(R.id.itemPopUpBackBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // close pop up and return to previous activity
                finish();
            }
        });

        int productID = getIntent().getIntExtra("Product ID", 0);
        Product product = productHandler.getProductByID(productID);

        String name = product.getProductName();
        double calories = product.getCalories();
        double fat = product.getFat();
        double carbs = product.getCarb();
        double protein = product.getProtein();
        int iconName = getIntent().getIntExtra("Product Icon", 0);

        List<Price> prices = productHandler.allStoreSortedPrice(product);

        TextView nameTextView = findViewById(R.id.searchedProductName);
        TextView caloriesTextView = findViewById(R.id.caloriesLabel);
        TextView fatTextView = findViewById(R.id.fatLabel);
        TextView carbsTextView = findViewById(R.id.carbLabel);
        TextView proteinTextView = findViewById(R.id.proteinLabel);

        ImageView productIconImgView = findViewById(R.id.productPopUpIcon);


        // set icon for product
        productIconImgView.setImageResource(iconName);

        nameTextView.setText(name);
        caloriesTextView.setText("Calories: " + calories);
        fatTextView.setText("Fat: " + fat + "g");
        carbsTextView.setText("Carbs: " + carbs + "g");
        proteinTextView.setText("Protein: " + protein + "g");

        RecyclerView pricePerStoreList = findViewById(R.id.pricePerStoreList);
        pricePerStoreList.setLayoutManager(new LinearLayoutManager(this));
        ItemPopupAdapter adapter = new ItemPopupAdapter(this, prices);
        pricePerStoreList.setAdapter(adapter);
    }
}