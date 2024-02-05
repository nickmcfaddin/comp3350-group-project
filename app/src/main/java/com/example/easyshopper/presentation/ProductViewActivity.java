package com.example.easyshopper.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyshopper.R;

import java.util.ArrayList;

public class ProductViewActivity extends AppCompatActivity {

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

        String name = getIntent().getStringExtra("Product Name");
        double calories = getIntent().getDoubleExtra("Calories",0);
        double fat = getIntent().getDoubleExtra("Fat",0);
        double carbs = getIntent().getDoubleExtra("Carbs",0);
        double protein = getIntent().getDoubleExtra("Protein",0);
        int iconName = getIntent().getIntExtra("Product Icon", 0);
        ArrayList<String> pricePerStore = getIntent().getStringArrayListExtra("List of Prices");

        TextView nameTextView = findViewById(R.id.searchedProductName);
        TextView caloriesTextView = findViewById(R.id.caloriesLabel);
        TextView fatTextView = findViewById(R.id.fatLabel);
        TextView carbsTextView = findViewById(R.id.carbLabel);
        TextView proteinTextView = findViewById(R.id.proteinLabel);

        ImageView productIconImgView = findViewById(R.id.productPopUpIcon);
        RecyclerView pricePerStoreList = findViewById(R.id.pricePerStoreList);

        // set icon for product
        productIconImgView.setImageResource(iconName);

        nameTextView.setText(name);
        caloriesTextView.setText("Calories: " + calories);
        fatTextView.setText("Fat: " + fat + "g");
        carbsTextView.setText("Carbs: " + carbs + "g");
        proteinTextView.setText("Protein: " + protein + "g");

        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pricePerStore);
        // pricePerStoreList.setAdapter(adapter);
    }
}