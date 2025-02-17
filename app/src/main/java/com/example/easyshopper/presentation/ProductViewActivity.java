package com.example.easyshopper.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Price;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.presentation.adapter.ItemPopupAdapter;

import java.text.DecimalFormat;
import java.util.List;

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

        //get values passed to the intent
        int productID = getIntent().getIntExtra("productID", 0);

        //get product with associating id and its values
        Product product = ProductHandler.getProductByID(productID);
        String name = product.getProductName();
        double calories = product.getCalories();

        // Create a DecimalFormat object with the desired format
        DecimalFormat df = new DecimalFormat("#.#");
        // Format the calories value to have a maximum of two decimal places
        String formattedCalories = df.format(calories);

        double fat = product.getFat();
        double carbs = product.getCarb();
        double protein = product.getProtein();
        String xmlFileName = "icon_" + name.toLowerCase().replace(" ", "_");
        List<Price> prices = ProductHandler.allStoreSortedPrice(product);

        //get components from xml
        TextView nameTextView = findViewById(R.id.searchedProductName);
        TextView caloriesTextView = findViewById(R.id.caloriesLabel);
        TextView fatTextView = findViewById(R.id.fatLabel);
        TextView carbsTextView = findViewById(R.id.carbLabel);
        TextView proteinTextView = findViewById(R.id.proteinLabel);
        ImageView productIconImgView = findViewById(R.id.productPopUpIcon);

        // set icon for product
        Context context = productIconImgView.getContext();
        int id = context.getResources().getIdentifier(xmlFileName, "drawable", context.getPackageName());
        productIconImgView.setImageResource(id);

        // set product nutritional facts
        nameTextView.setText(name);
        caloriesTextView.setText("Calories: " + formattedCalories);
        fatTextView.setText("Fat: " + fat + "g");
        carbsTextView.setText("Carbs: " + carbs + "g");
        proteinTextView.setText("Protein: " + protein + "g");

        //  set adapter for price per store of product
        RecyclerView pricePerStoreList = findViewById(R.id.pricePerStoreList);
        pricePerStoreList.setLayoutManager(new LinearLayoutManager(this));
        ItemPopupAdapter adapter = new ItemPopupAdapter(this, prices);
        pricePerStoreList.setAdapter(adapter);
    }
}