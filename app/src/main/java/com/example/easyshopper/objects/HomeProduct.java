package com.example.easyshopper.objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class HomeProduct extends Product implements Serializable {
    int stockQuantity;
    int desiredQuantity;
    List<String> expiryDates; // ISO 8601 format (yyyy-MM-dd)

    public HomeProduct(int productID, String productName, double fat, double carb, double protein, int stockQuantity, int desiredQuantity, int lifeTimeDays, List<String> expiryDates) {
        super(productID, productName, fat, carb, protein, lifeTimeDays);

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

    //Not included in unit testing as this is pulling from the db
    public HomeProduct(Product product, int stockQuantity, int desiredQuantity, List<String> expiryDates){
        super(product.getProductID(), product.getProductName(), product.getFat(), product.getCarb(), product.getProtein(), product.getLifeTimeDays());
        this.stockQuantity = stockQuantity;
        this.desiredQuantity = desiredQuantity;
        this.expiryDates = expiryDates;
    }

    // GETTERS
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
    public void incrementStockQuantityBy1(){
        stockQuantity++;
        addExpiryDate();
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

    public void addExpiryDate() {
        // get current date when user add the product
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, getLifeTimeDays());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String newExpiryDate = sdf.format(calendar.getTime());

        expiryDates.add(newExpiryDate);
    }

    public void setExpiryDates(List<String> expiryDates){
        this.expiryDates = expiryDates;
    }

}