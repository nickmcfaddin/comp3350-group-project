package com.example.easyshopper.objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class HomeProduct extends Product {
    int stockQuantity;
    int desiredQuantity;
    List<String> expiryDates; // ISO 8601 format (yyyy-MM-dd)

    public HomeProduct(int productID, String productName, double fat, double carb, double protein, int stockQuantity, int desiredQuantity, List<String> expiryDates) {
        super(productID, productName, fat, carb, protein, 0);

        // Validate expiryDates size
        if (expiryDates.size() != stockQuantity) {
            this.stockQuantity = 0; // Reset stockQuantity to 0
            this.desiredQuantity = desiredQuantity;
            this.expiryDates = new ArrayList<>();
            return; // Exit the constructor
        }

        this.stockQuantity = stockQuantity;
        this.desiredQuantity = desiredQuantity;
        this.expiryDates = expiryDates;
    }

    public HomeProduct(Product product, int stockQuantity, int desiredQuantity, List<String> expiryDates){
        super(product.getProductID(), product.getProductName(), product.getFat(), product.getCarb(), product.getProtein(), 0);
        this.stockQuantity = stockQuantity;
        this.desiredQuantity = desiredQuantity;
        this.expiryDates = expiryDates;
    }

    // GETTERS
    public String getHomeProductName() {
        return super.getProductName();
    }

    public int getHomeProductStockQuantity(){
        return stockQuantity;
    }

    public int getHomeProductDesiredQuantity(){
        return desiredQuantity;
    }

    public List<String> getHomeProductExpiryDates(){
        return expiryDates;
    }

    // increase and decrease stock and desired quantity
    public void incrementStockQuantityBy1(String date){
        if (addExpiryDate(date)) {
            stockQuantity++;
        }
    }

    public void incrementDesiredQuantityBy1(){
        desiredQuantity++;
    }

    public void decreaseStockQuantityBy1(){
        if (stockQuantity > 0){
            stockQuantity--;
            removeEarliestExpiryDate();
        }
    }

    public void decreaseDesiredQuantityBy1(){
        if (desiredQuantity > 0){
            desiredQuantity--;
        }
    }

    public List<String> getSortedExpiryDatesAscending() {
        // Convert ISO 8601 date strings to LocalDate objects
        List<LocalDate> localDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        for (String date : expiryDates) {
            localDates.add(LocalDate.parse(date, formatter));
        }

        // Sort LocalDate objects in ascending order
        Collections.sort(localDates);

        // Convert sorted LocalDate objects back to ISO 8601 date strings
        List<String> sortedDates = new ArrayList<>();
        for (LocalDate date : localDates) {
            sortedDates.add(date.format(formatter));
        }

        return sortedDates;
    }

    public List<String> getSortedExpiryDatesDescending() {
        // Convert ISO 8601 date strings to LocalDate objects
        List<LocalDate> localDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        for (String date : expiryDates) {
            localDates.add(LocalDate.parse(date, formatter));
        }

        // Sort LocalDate objects in descending order
        localDates.sort(Collections.reverseOrder());

        // Convert sorted LocalDate objects back to ISO 8601 date strings
        List<String> sortedDates = new ArrayList<>();
        for (LocalDate date : localDates) {
            sortedDates.add(date.format(formatter));
        }

        return sortedDates;
    }

    public void removeEarliestExpiryDate(){
        List<String> sortedExpiryDateAscending = getSortedExpiryDatesAscending();

        if (sortedExpiryDateAscending.size() > 0){
            String removedExpiryDate = sortedExpiryDateAscending.get(0);
            expiryDates.remove(removedExpiryDate);
            // will remove only 1 instance per method
        }
    }

    public boolean addExpiryDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        // regular expression pattern for ISO 8601 date format (yyyy-MM-dd)
        String iso8601Pattern = "\\d{4}-\\d{2}-\\d{2}";

        // check if the date matches the ISO 8601 format
        if (Pattern.matches(iso8601Pattern, date)) {
            // If the date matches the format, attempt to parse it
            try {
                LocalDate parsedDate = LocalDate.parse(date, formatter);
                // If parsing is successful, add the date to list of expiry dates
                expiryDates.add(date);
                return true;
            } catch (Exception e) {
                // Handle any parsing exceptions here
                System.out.println("Error parsing date: " + e.getMessage());
            }
        } else {
            // If the date does not match the ISO 8601 format, inform the user
            System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
        }

        return false;
    }

}