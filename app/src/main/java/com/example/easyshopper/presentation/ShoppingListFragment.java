package com.example.easyshopper.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.easyshopper.R;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.example.easyshopper.presentation.adapter.ShoppingListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment {
    private StoreHandler storeHandler = new StoreHandler();
    private ProductHandler productHandler = new ProductHandler();
    private ShoppingListHandler shoppingListHandler = new ShoppingListHandler();

    private List<ShoppingList> shoppingLists = shoppingListHandler.getAllShoppingLists();
    private ShoppingListAdapter shoppingListAdapter;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    public static ShoppingListFragment newInstance() {
        return new ShoppingListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_shopping_list, container, false);

        initComponents(rootView);

        return rootView;
    }

    public void addProductDialog() {
        List<Product> productList = productHandler.getAllProducts();

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[productList.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Products to Add:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<Product> listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, productList);
        listView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        // Set multi-choice items and listen for changes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedItems[position] = !checkedItems[position];
            checkedTextView.setChecked(checkedItems[position]);
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> clickedProducts = new ArrayList<>();

                //get list of clicked products
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedProducts.add(productList.get(i));
                    }
                }

                //check if user has not clicked any product
                if(!clickedProducts.isEmpty()){
                    addProductsToStoreDialog(clickedProducts);
                }

                alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }

    public void addProductsToStoreDialog(List<Product> clickedProducts) {
        List<ShoppingList> shoppingLists = shoppingListHandler.getAllShoppingLists();

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[shoppingLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Shopping lists to add Products into:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<ShoppingList> listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, shoppingLists);
        listView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        // Set multi-choice items and listen for changes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedItems[position] = !checkedItems[position];
            checkedTextView.setChecked(checkedItems[position]);
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShoppingList> clickedShoppingLists = new ArrayList<>();

                //get list of clicked products
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedShoppingLists.add(shoppingLists.get(i));
                    }
                }

                //add clicked products to clicked shopping lists
                for(ShoppingList shoppingList : shoppingLists) {
                    for(Product product : clickedProducts) {
                        shoppingListHandler.addItemToList(product, shoppingList);
                    }
                }

                updateShoppingListView();

                alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }

    public void createListDialog() {
        final Store[] selectedStore = {null};

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_list, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        List<Store> storeList = storeHandler.getExistingStores();
        AutoCompleteTextView autoCompleteTextView = dialogView.findViewById(R.id.dropdown_field);
        ArrayAdapter<Store> listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, storeList);
        autoCompleteTextView.setAdapter(listAdapter);

        EditText editText = dialogView.findViewById(R.id.list_name_input);
        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        //track what store user selected
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStore[0] = (Store) parent.getItemAtPosition(position);
            }
        });

        //set behaviour for buttons
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user has entered values for both inputs
                Store store = selectedStore[0];
                String userString = editText.getText().toString().trim();
                if(store == null || userString.isEmpty())
                {
                    return;
                }

                //create shopping list and update the list view
                shoppingListHandler.createShoppingList(userString, store);

                updateShoppingListView();

                alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }

    public void deleteListDialog() {
        List<ShoppingList> shoppingLists = shoppingListHandler.getAllShoppingLists();

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_multiple_choice, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        // Initialize boolean array to track selected categories
        boolean[] checkedItems = new boolean[shoppingLists.size()];

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.input_dialog_title);
        dialogTitle.setText("Choose Shopping lists to add Products into:");

        ListView listView = dialogView.findViewById(R.id.list_view);
        ArrayAdapter<ShoppingList> listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, shoppingLists);
        listView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        // Set multi-choice items and listen for changes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedItems[position] = !checkedItems[position];
            checkedTextView.setChecked(checkedItems[position]);
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShoppingList> clickedShoppingLists = new ArrayList<>();

                //get list of clicked lists
                for(int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i]){
                        clickedShoppingLists.add(shoppingLists.get(i));
                    }
                }

                //remove shopping lists
                for(ShoppingList shoppingList : clickedShoppingLists) {
                    shoppingListHandler.removeShoppingList(shoppingList);
                }

                updateShoppingListView();

                alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Show the dialog
        alertDialog.show();
    }

    private void updateShoppingListView() {
        shoppingLists = shoppingListHandler.getAllShoppingLists();

        shoppingListAdapter.updateData(shoppingLists);
    }
    private void initComponents(View rootView) {
        ExpandableListView shoppingListView = rootView.findViewById(R.id.shoppingListView);
        shoppingListAdapter = new ShoppingListAdapter(getContext(), shoppingLists);
        shoppingListView.setAdapter(shoppingListAdapter);

        ImageButton addButton = rootView.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(getContext(), R.style.MyMenuStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, v);
                popupMenu.getMenuInflater().inflate(R.menu.shopping_list_add_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.addProduct) {
                            addProductDialog();
                        } else if (menuItem.getItemId() == R.id.createList) {
                            createListDialog();
                        } else if (menuItem.getItemId() == R.id.deleteList) {
                            deleteListDialog();
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}