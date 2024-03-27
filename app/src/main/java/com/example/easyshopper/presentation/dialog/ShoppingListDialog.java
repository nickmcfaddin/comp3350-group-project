package com.example.easyshopper.presentation.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyshopper.R;
import com.example.easyshopper.application.Dialog;
import com.example.easyshopper.logic.ProductHandler;
import com.example.easyshopper.logic.ShoppingListHandler;
import com.example.easyshopper.logic.StoreHandler;
import com.example.easyshopper.logic.exceptions.InvalidShoppingListException;
import com.example.easyshopper.objects.Product;
import com.example.easyshopper.objects.ShoppingList;
import com.example.easyshopper.objects.Store;
import com.google.android.material.textfield.TextInputLayout;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ShoppingListDialog extends ProductListDialog {
    public ShoppingListDialog(Context context) {
        super(context);
    }

    //show a prompt asking the user what products they would like added to their lists
    public void chooseProductsDialog() {
        chooseProductsDialog(new ArrayList<>(ShoppingListHandler.getAllShoppingLists()));
    }

    public void deleteListDialog() {
        deleteListDialog(new ArrayList<>(ShoppingListHandler.getAllShoppingLists()));
    }

    //show a prompt asking the user what store they'd like to make a shopping list for
    public void createListDialog() {
        final Store[] selectedStore = {null};

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_list, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        List<Store> storeList = StoreHandler.getExistingStores();
        AutoCompleteTextView autoCompleteTextView = dialogView.findViewById(R.id.dropdown_field);
        ArrayAdapter<Store> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, storeList);
        autoCompleteTextView.setAdapter(listAdapter);

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
                if(store == null)
                {
                    return;
                }

                //create shopping list and update the list view
                try {
                    ShoppingListHandler.createShoppingList(store);
                } catch (InvalidShoppingListException e) {
                    Dialog.showErrorMessageDialog(e);
                    return;
                }

                //update the displayed list
                if(dynamicListAdapter != null) {
                    dynamicListAdapter.updateData();
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

    //show a prompt asking the user what shopping list to export
    public void exportListDialog(Context context) {
        final ShoppingList[] selectedShoppingList = {null};

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_list, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        List<ShoppingList> storeList = ShoppingListHandler.getAllShoppingLists();
        AutoCompleteTextView autoCompleteTextView = dialogView.findViewById(R.id.dropdown_field);
        ArrayAdapter<ShoppingList> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, storeList);
        autoCompleteTextView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        //track what shopping list user selected
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedShoppingList[0] = (ShoppingList) parent.getItemAtPosition(position);
            }
        });

        //set behaviour for buttons
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user has entered values for both inputs
                ShoppingList shoppingList = selectedShoppingList[0];

                if(shoppingList == null)
                {
                    return;
                }

                exportTypeDialog(selectedShoppingList[0], context);

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

    //show a prompt asking the user what type of file they'd like to export
    public void exportTypeDialog(ShoppingList shoppingList, Context context) {
        final String[] selectedExportType = {null};

        //Create alert and link it to our custom dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_list, null);
        alert.setView(dialogView);
        final AlertDialog alertDialog = alert.create();

        //get and init components
        TextView dialogTitle = dialogView.findViewById(R.id.store_select_title);
        dialogTitle.setText("Choose export file type:");

        TextInputLayout dialogTextInputLayout = dialogView.findViewById(R.id.textInputLayout);
        dialogTextInputLayout.setHint("Export Type");

        List<String> exportType = new ArrayList<>();
        exportType.add("TXT");
        exportType.add("PDF");
        AutoCompleteTextView autoCompleteTextView = dialogView.findViewById(R.id.dropdown_field);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, exportType);
        autoCompleteTextView.setAdapter(listAdapter);

        Button cancelButton = dialogView.findViewById(R.id.cancel_btn);
        Button submitButton = dialogView.findViewById(R.id.submit_btn);

        //track what shopping list user selected
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedExportType[0] = (String) parent.getItemAtPosition(position);
            }
        });

        //set behaviour for buttons
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user has entered values for both inputs
                String exportedType = selectedExportType[0];

                if(exportedType == null)
                {
                    return;
                }

                if (Objects.equals(selectedExportType[0], "TXT")){
                    exportToTextFile(shoppingList, context);
                }
                else if (Objects.equals(selectedExportType[0], "PDF")) {
                    exportToPDFFile(shoppingList, context);
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

    //show a prompt asking the user what shopping list they'd like to export
    private void exportToTextFile(ShoppingList shoppingList, Context context) {
        String content = convertContentShoppingListToString(shoppingList);
        String shoppingListName = shoppingList.getListName();
        String fileName = shoppingListName.toLowerCase() + ".txt";

        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(directory, fileName);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Successfully exported to text file " + fileName + "!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error exporting content!", Toast.LENGTH_SHORT).show();
        }
    }

    private void exportToPDFFile(ShoppingList shoppingList, Context context) {
        // Get the shopping list's content
        String shoppingListName = shoppingList.getListName();
        double shoppingListTotal = ShoppingListHandler.getCartTotal(shoppingList);
        List<Product> shoppingListCart = shoppingList.getCart();
        Store shoppingListStore = shoppingList.getStore();

        // Create a file name with the shopping list name and PDF extension
        String fileName = shoppingListName.toLowerCase() + ".pdf";

        // Get the directory for storing PDF files
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(directory, fileName);

        try {
            // Create a PdfWriter object with the output file path
            PdfWriter writer = new PdfWriter(file);

            // Create a PdfDocument object associated with the writer
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Create a Document object from the PdfDocument
            Document document = new Document(pdfDocument);

            // Add PDF Title
            Paragraph pdfTitle = new Paragraph(shoppingListName + " Shopping List:")
                    .setFont(PdfFontFactory.createFont())
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(pdfTitle);

            // Add header for shopping list
            Paragraph listTitle = new Paragraph()
                    .setFontSize(15);

            // Add the list name to the paragraph
            Text listName = new Text("Total: ")
                    .setFont(PdfFontFactory.createFont())
                    .setBold();
            listTitle.add(listName);

            // Add the list price to the paragraph
            Text listTotal = new Text("$" + shoppingListTotal)
                    .setFont(PdfFontFactory.createFont());
            listTitle.add(listTotal);

            document.add(listTitle);

            // Add header for shopping list content
            Paragraph listContent = new Paragraph("Products:")
                    .setFont(PdfFontFactory.createFont())
                    .setFontSize(15)
                    .setBold();
            document.add(listContent);

            // Add bullet list for products
            com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List()
                    .setSymbolIndent(12)
                    .setListSymbol("•")
                    .setFont(PdfFontFactory.createFont())
                    .setFontSize(12);

            for (Product product : shoppingListCart) {
                String prodName = product.getProductName();
                double prodPrice = ProductHandler.getPriceOfProductInStore(product, shoppingListStore);

                list.add(prodName + " - $" + prodPrice);
            }

            document.add(list);

            // Close the document
            document.close();

            // Show a success message
            Toast.makeText(context, "Successfully exported to PDF file " + fileName + "!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error exporting content!", Toast.LENGTH_SHORT).show();
        }
    }

    private String convertContentShoppingListToString(ShoppingList shoppingList){
        StringBuilder sb = new StringBuilder();
        String shoppingListName = shoppingList.getListName();
        double shoppingListTotal = ShoppingListHandler.getCartTotal(shoppingList);
        List<Product> shoppingListCart = shoppingList.getCart();
        Store shoppingListStore = shoppingList.getStore();

        sb.append("Shopping List: ").append(shoppingListName).append("\n");

        sb.append("\nTotal: $").append(shoppingListTotal).append("\n");

        sb.append("\nProducts:\n");

        for (Product product : shoppingListCart) {
            String prodName = product.getProductName();
            double prodPrice = ProductHandler.getPriceOfProductInStore(product, shoppingListStore);

            sb.append("- ").append(prodName).append(": $").append(prodPrice).append("\n");
        }

        return sb.toString();
    }

}
