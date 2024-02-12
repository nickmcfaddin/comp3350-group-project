package com.example.easyshopper.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.presentation.adapter.ProductSearchAdapter;
import com.example.easyshopper.presentation.adapter.ProductViewInterface;

import java.io.Serializable;
import java.util.List;

public class SearchFragment extends Fragment implements ProductViewInterface {
    //keys for serializable objects
    private static final String PRODUCT_HANDLER_KEY = "productHandler";
    private static final String STORE_HANDLER_KEY = "storeHandler";
    private static final String PRODUCT_ID_KEY = "productID";

    //communicate with the logic layer
    private ProductHandler productHandler;
    private StoreHandler storeHandler;

    //UI components
    private SearchView searchView;
    private RecyclerView productListView;
    private ProductSearchAdapter productSearchAdapter;
    private List<Product> productList;

    public SearchFragment(){

    }

    public SearchFragment(ProductHandler productHandler, StoreHandler storeHandler) {
        this.productHandler = productHandler;
        this.storeHandler = storeHandler;
        productList = productHandler.getAllProducts();
    }

    public static SearchFragment newInstance(ProductHandler productHandler, StoreHandler storeHandler) {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment(productHandler, storeHandler);
        args.putSerializable(PRODUCT_HANDLER_KEY, productHandler);
        args.putSerializable(STORE_HANDLER_KEY, storeHandler);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            productHandler = (ProductHandler) savedInstanceState.getSerializable(PRODUCT_HANDLER_KEY);
            storeHandler = (StoreHandler) savedInstanceState.getSerializable(STORE_HANDLER_KEY);
        } else if (getArguments() != null) {
            productHandler = (ProductHandler) getArguments().getSerializable(PRODUCT_HANDLER_KEY);
            storeHandler = (StoreHandler) getArguments().getSerializable(STORE_HANDLER_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search, container, false);

        //set behaviour for components
        initComponents(rootView);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PRODUCT_HANDLER_KEY, productHandler);
        outState.putSerializable(STORE_HANDLER_KEY, storeHandler);
        super.onSaveInstanceState(outState);
    }

    private void searchProducts(String query){
        //get list of products that contain user's inputted string
        productList = productHandler.getProductsByName(query);

        //update list that is displayed
        productSearchAdapter.updateData(productList);
    }

    @Override
    public void onItemClick(int position) {
        //get product that has been clicked
        Product clickedProduct = productList.get(position);

        //start an popup showing more information about the clicked product
        Intent intent = new Intent(getActivity(), ProductViewActivity.class);
        intent.putExtra(PRODUCT_ID_KEY, clickedProduct.getProductID());
        intent.putExtra(PRODUCT_HANDLER_KEY, productHandler);
        intent.putExtra(STORE_HANDLER_KEY, storeHandler);
        startActivity(intent);
    }

    private void initComponents(View rootView) {
        searchView = rootView.findViewById(R.id.searchView);

        //set adapter for recyclerview
        productListView = rootView.findViewById(R.id.productListView);
        productListView.setLayoutManager(new LinearLayoutManager(requireContext()));
        productSearchAdapter = new ProductSearchAdapter(getContext(), productList, this);
        productListView.setAdapter(productSearchAdapter);

        //call method on user input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchProducts(query);
                return true;
            }
        });
    }
}