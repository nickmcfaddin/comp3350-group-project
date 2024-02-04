package com.example.easyshopper.presentation;

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
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.presentation.adapter.ProductSearchAdapter;
import com.example.easyshopper.presentation.adapter.ProductViewInterface;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements ProductViewInterface {
    //communicate with the logic layer
    private ProductHandler productHandler = new ProductHandler();

    //UI components
    private SearchView searchView;
    private RecyclerView productListView;
    private ProductSearchAdapter productSearchAdapter;
    private List<Product> productList = productHandler.getAllProducts();

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search, container, false);

        initComponents(rootView);

        return rootView;
    }

    private void searchProducts(String query){
        //get list of products that contain user's inputted string
        productList = productHandler.getProductsByName(query);

        //update list that is displayed
        productSearchAdapter.updateData(productList);
    }

    @Override
    public void onItemClick(int position) {

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