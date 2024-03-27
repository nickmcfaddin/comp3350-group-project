package com.example.easyshopper.persistence.hsqldb;

public class ColumnNames {
    /* DB TABLES:
    STORES:
        StoreID
        Name
    PRODUCTS:
        ProductID
        Name
        Protein
        Carbs
        Fat
        LifetimeDays
    PRICES:
        StoreID
        ProductID
        Price
    USER:
        UserID
        Name
    PRODUCTLIST:
        ListID
        StoreID
        UserID
    CONTAINS:
        ListID
        ProductID
        Quantity
    HOMEPRODUCTS:
        ProductID
        StockQuantity
        DesiredQuantity
    EXPIRYDATES:
        ProductID
        ExpiryDate
    */

    public static final String STORE_ID = "StoreID";
    public static final String PRODUCT_ID = "ProductID";
    public static final String NAME = "Name";
    public static final String PRICE = "Price";
    public static final String STOCK_QUANTITY = "StockQuantity";
    public static final String DESIRED_QUANTITY = "DesiredQuantity";
    public static final String EXPIRY_DATE = "ExpiryDate";
    public static final String LIST_ID = "ListID";
    public static final String USER_ID = "UserID";
    public static final String PROTEIN = "Protein";
    public static final String CARBS = "Carbs";
    public static final String FAT = "Fat";
    public static final String LIFETIME_DAYS = "LifetimeDays";
}
