package com.example.easyshopper.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.easyshopper.R;

import java.util.ArrayList;

public class ProductViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        String name = getIntent().getStringExtra("Product Name");
        double calories = getIntent().getDoubleExtra("Calories",0);
        double fat = getIntent().getDoubleExtra("Fat",0);
        double carbs = getIntent().getDoubleExtra("Carbs",0);
        double protein = getIntent().getDoubleExtra("Protein",0);
        ArrayList<String> pricePerStore = getIntent().getStringArrayListExtra("List of Prices");

        TextView nameTextView = findViewById(R.id.searchedProductName);
        TextView caloriesTextView = findViewById(R.id.caloriesLabel);
        TextView fatTextView = findViewById(R.id.fatLabel);
        TextView carbsTextView = findViewById(R.id.carbLabel);
        TextView proteinTextView = findViewById(R.id.proteinLabel);

        RecyclerView pricePerStoreList = findViewById(R.id.pricePerStoreList);

        nameTextView.setText(name);
        caloriesTextView.setText("Calories: " + calories);
        fatTextView.setText("Fat: " + fat + "g");
        carbsTextView.setText("Carbs: " + carbs + "g");
        proteinTextView.setText("Protein: " + protein + "g");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pricePerStore);
        pricePerStoreList.setAdapter(adapter);
    }
}