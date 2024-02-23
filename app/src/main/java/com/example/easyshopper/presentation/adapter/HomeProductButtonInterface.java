package com.example.easyshopper.presentation.adapter;

import android.view.View;

public interface HomeProductButtonInterface {
    // 4 strings for button type: "addStock", "removeStock", "addDesired", "removeDesired"
    void onButtonClick(View view, int position, String buttonType);
}

