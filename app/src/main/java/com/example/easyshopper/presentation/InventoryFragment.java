package com.example.easyshopper.presentation;

import android.content.Context;
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

import com.example.easyshopper.R;
import com.example.easyshopper.logic.HomeInventoryHandler;
import com.example.easyshopper.objects.HomeProduct;
import com.example.easyshopper.presentation.adapter.HomeProductButtonInterface;
import com.example.easyshopper.presentation.adapter.HomeProductHiddenAdapter;
import com.example.easyshopper.presentation.adapter.HomeProductStockAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment implements HomeProductButtonInterface {
    //keys for serializable objects
    private static final String HOME_INVENTORY_HANDLER_KEY = "homeInventoryHandler";

    //communicate with the logic layer
    private HomeInventoryHandler homeInventoryHandler;

    private InventoryDialog inventoryDialog;

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
        homeProductStockAdapter = new HomeProductStockAdapter(getContext(), homeProductInventoryList, this, R.id.stockHomeInventoryView);
        stockHomeProductView.setAdapter(homeProductStockAdapter);

        // set adapter for hidden products recyclerview
        hiddenHomeProductView = rootView.findViewById(R.id.hiddenHomeInventoryView);
        hiddenHomeProductView.setLayoutManager(new LinearLayoutManager(requireContext()));
        homeProductHiddenAdapter = new HomeProductHiddenAdapter(getContext(), homeProductHiddenList, this, R.id.hiddenHomeInventoryView);
        hiddenHomeProductView.setAdapter(homeProductHiddenAdapter);

        // allow for dialogs to be displayed in this class
        inventoryDialog = new InventoryDialog(getContext(), homeInventoryHandler, homeProductStockAdapter, homeProductHiddenAdapter);

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
        homeProductInventoryList = homeInventoryHandler.getSortedStockProduct();
        homeProductStockAdapter.updateData(homeProductInventoryList);
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

    @Override
    public void onButtonClick(View view, int position, String buttonType, int recyclerViewId) {
        // Handle button click events here
        // Get the context for logging
        Context context = getContext();

        if (recyclerViewId == R.id.stockHomeInventoryView) {
            // Get the clicked HomeProduct from the stock inventory list
            HomeProduct clickedProduct = homeProductInventoryList.get(position);

            // Check the buttonType and update quantities accordingly
            switch (buttonType) {
                case "removeStock":
                    homeInventoryHandler.decreaseStockQuantityBy1(clickedProduct);
                    break;
                case "addStock":
                    inventoryDialog.showHomeProductAddExpiryDate(clickedProduct, userInput -> {
                        // Update homeProductInventoryList and homeProductHiddenList after the stock quantity is incremented
                        homeProductInventoryList = homeInventoryHandler.getStockProduct();
                        homeProductHiddenList = homeInventoryHandler.getHiddenProduct();
                    });

                    break;
                case "removeDesired":
                    homeInventoryHandler.decreaseDesiredQuantityBy1(clickedProduct);
                    break;
                case "addDesired":
                    homeInventoryHandler.incrementDesiredQuantityBy1(clickedProduct);
                    break;
                case "productName":
                    inventoryDialog.showHomeProductExpiryDate(clickedProduct);
                    break;
                default:
                    break;
            }
        } else if (recyclerViewId == R.id.hiddenHomeInventoryView) {
            // Get the clicked HomeProduct from the hidden list
            HomeProduct clickedProduct = homeProductHiddenList.get(position);

            // Check the buttonType and update quantities accordingly
            switch (buttonType) {
                case "removeStock":
                    homeInventoryHandler.decreaseStockQuantityBy1(clickedProduct);
                    break;
                case "addStock":
                    inventoryDialog.showHomeProductAddExpiryDate(clickedProduct, userInput -> {
                        // Update homeProductInventoryList and homeProductHiddenList after the stock quantity is incremented
                        homeProductInventoryList = homeInventoryHandler.getStockProduct();
                        homeProductHiddenList = homeInventoryHandler.getHiddenProduct();
                    });

                    break;
                case "removeDesired":
                    homeInventoryHandler.decreaseDesiredQuantityBy1(clickedProduct);
                    break;
                case "addDesired":
                    homeInventoryHandler.incrementDesiredQuantityBy1(clickedProduct);
                    break;
                case "productName":
                    inventoryDialog.showHomeProductExpiryDate(clickedProduct);
                    break;
                default:
                    break;
            }
        }

        // Notify the inventory adapter that the data has changed
        homeProductInventoryList = homeInventoryHandler.getStockProduct();
        homeProductHiddenList = homeInventoryHandler.getHiddenProduct();
        homeProductStockAdapter.updateData(homeProductInventoryList);
        homeProductHiddenAdapter.updateData(homeProductHiddenList);
    }
}
