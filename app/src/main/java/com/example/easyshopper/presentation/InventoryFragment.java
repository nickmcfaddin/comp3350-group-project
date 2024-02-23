package com.example.easyshopper.presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.presentation.adapter.HomeProductHiddenAdapter;
import com.example.easyshopper.presentation.adapter.HomeProductStockAdapter;
import com.example.easyshopper.presentation.adapter.ProductSearchAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {
    //keys for serializable objects
    private static final String HOME_INVENTORY_HANDLER_KEY = "homeInventoryHandler";

    //communicate with the logic layer
    private HomeInventoryHandler homeInventoryHandler;

    //UI components
    private RecyclerView stockHomeProductView;
    private RecyclerView hiddenHomeProductView;
    private HomeProductStockAdapter homeProductStockAdapter;
    private HomeProductHiddenAdapter homeProductHiddenAdapter;
    private ImageButton sortButton;
    private LinearLayout hiddenProductTitle;
    private ImageView arrowImage;
    private List<HomeProduct> homeProductInventoryList;
    private List<HomeProduct> homeProductHiddenList;

    public InventoryFragment() {
        // Required empty public constructor
    }

    public InventoryFragment(HomeInventoryHandler homeInventoryHandler) {
        this.homeInventoryHandler = homeInventoryHandler;
        homeProductInventoryList = homeInventoryHandler.getStockProduct();
        homeProductHiddenList = homeInventoryHandler.getHiddenProduct();
    }

    public static InventoryFragment newInstance(HomeInventoryHandler homeInventoryHandler) {
        InventoryFragment fragment = new InventoryFragment(homeInventoryHandler);
        Bundle args = new Bundle();
        args.putSerializable(HOME_INVENTORY_HANDLER_KEY, homeInventoryHandler);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            homeInventoryHandler = (HomeInventoryHandler) savedInstanceState.getSerializable(HOME_INVENTORY_HANDLER_KEY);
        } else if (getArguments() != null) {
            homeInventoryHandler = (HomeInventoryHandler) getArguments().getSerializable(HOME_INVENTORY_HANDLER_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_inventory, container, false);

        //set behaviour for components
        initComponents(rootView);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(HOME_INVENTORY_HANDLER_KEY, homeInventoryHandler);
        super.onSaveInstanceState(outState);
    }

    private void initComponents(View rootView) {
        homeProductInventoryList = homeInventoryHandler.getStockProduct();
        homeProductHiddenList = homeInventoryHandler.getHiddenProduct();

        // set adapter for stock products recyclerview
        stockHomeProductView = rootView.findViewById(R.id.stockHomeInventoryView);
        stockHomeProductView.setLayoutManager(new LinearLayoutManager(requireContext()));
        homeProductStockAdapter = new HomeProductStockAdapter(getContext(), homeProductInventoryList, homeInventoryHandler);
        stockHomeProductView.setAdapter(homeProductStockAdapter);

        // set adapter for hidden products recyclerview
        hiddenHomeProductView = rootView.findViewById(R.id.hiddenHomeInventoryView);
        hiddenHomeProductView.setLayoutManager(new LinearLayoutManager(requireContext()));
        homeProductHiddenAdapter = new HomeProductHiddenAdapter(getContext(), homeProductHiddenList, homeInventoryHandler);
        hiddenHomeProductView.setAdapter(homeProductHiddenAdapter);

        // sort button
        sortButton = rootView.findViewById(R.id.sortButton);

        // Set OnClickListener for the sort button
        sortButton.setOnClickListener(v -> sortStockHomeProduct());

        // hidden products click
        hiddenProductTitle = rootView.findViewById(R.id.hiddenProductLayout);

        // set OnClickListener for hidden products click
        hiddenProductTitle.setOnClickListener(v -> showAndHideHiddenProduct(rootView));
    }

    private void sortStockHomeProduct() {
        List<HomeProduct> sortedHomeProduct = homeInventoryHandler.getSortedStockProduct();
        homeProductStockAdapter.updateData(sortedHomeProduct);
    }


    private void showAndHideHiddenProduct(View view) {
        int visibility = hiddenHomeProductView.getVisibility();
        arrowImage = view.findViewById(R.id.hiddenProductArrow);

        if (visibility == 8){
            // the view is GONE, which means hidden products is not shown
            hiddenHomeProductView.setVisibility(View.VISIBLE);
            arrowImage.setImageResource(R.drawable.icon_arrow_down);
        }
        else if (visibility == 0){
            // the view is VISIBLE, which means hidden products is shown
            hiddenHomeProductView.setVisibility(View.GONE);
            arrowImage.setImageResource(R.drawable.icon_arrow_up);
        }
    }
}