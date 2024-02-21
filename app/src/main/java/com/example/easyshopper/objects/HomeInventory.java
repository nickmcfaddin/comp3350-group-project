package com.example.easyshopper.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeInventory implements Serializable{
    ArrayList<HomeProduct> stock;
    ArrayList<Integer> desiredQuantity;

    ArrayList<Integer> stockQuantity;

    // Using singleton for the object
    private HomeInventory(){
        stock = new ArrayList<>();
        desiredQuantity = new ArrayList<>();
        stockQuantity = new ArrayList<>();
    }

    // GETTERS
    public ArrayList<HomeProduct> getHomeProduct(){
        return stock;
    }

    public ArrayList<Integer> getDesiredQuantity(){
        return desiredQuantity;
    }

    public ArrayList<Integer> getStockQuantity(){
        return stockQuantity;
    }

    //}

    // METHODS
    public void addHomeProductToStock(HomeProduct homeProduct){
        if (homeProduct != null && !stock.contains(homeProduct)){
            stock.add(homeProduct);
        }
    }

    public void removeHomeProductFromStock(HomeProduct homeProduct){
        if (homeProduct != null){
            stock.remove(homeProduct);
        }
    }

    public void addOneToHomeProductDesiredQuantity(HomeProduct homeProduct){
        if (homeProduct != null){
            int index = stock.indexOf(homeProduct);

            if (index != -1) {
                int curDesiredQuantity = desiredQuantity.get(index);

                desiredQuantity.set(index, curDesiredQuantity + 1);
            }
        }
    }

    public void addOneToHomeProductStockQuantity(HomeProduct homeProduct){
        if (homeProduct != null){
            int index = stock.indexOf(homeProduct);

            if (index != -1) {
                int curStockQuantity = stockQuantity.get(index);

                stockQuantity.set(index, curStockQuantity + 1);
            }
        }
    }

    public void removeOneFromHomeProductDesiredQuantity(HomeProduct homeProduct){
        if (homeProduct != null){
            int index = stock.indexOf(homeProduct);

            if (index != -1) {
                int curDesiredQuantity = desiredQuantity.get(index);

                if (curDesiredQuantity > 0) {
                    desiredQuantity.set(index, curDesiredQuantity - 1);
                }
            }
        }
    }

    public void removeOneFromHomeProductStockQuantity(HomeProduct homeProduct){
        if (homeProduct != null){
            int index = stock.indexOf(homeProduct);

            if (index != -1) {
                int curStockQuantity = stockQuantity.get(index);

                if (curStockQuantity > 0) {
                    stockQuantity.set(index, curStockQuantity - 1);
                }
            }
        }
    }



}
