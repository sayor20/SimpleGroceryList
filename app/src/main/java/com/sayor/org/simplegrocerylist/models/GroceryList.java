package com.sayor.org.simplegrocerylist.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


// Singleton class for arraylist to share across the app
public class GroceryList {
    private static GroceryList groceryList;
    private List<Grocery> mGroceries;

    public GroceryList(Context context) {
        if(context!=null)
            mGroceries = new ArrayList<>();
            // dummy data to get started
            mGroceries.add(new Grocery("Safeway", "Buy pizza from safeway", 3, false));
            mGroceries.add(new Grocery("Whole Foods ", "Buy carrots and beans from whole foods", 2, true));
    }

    public List<Grocery> getmGroceries() {
        return mGroceries;
    }

    public Grocery getmGrocery(int pos) {
        return mGroceries.get(pos);
    }

    public void addGrocery(Grocery groc){
        mGroceries.add(groc);
    }

    public void addGrocery(int pos, Grocery groc){
        mGroceries.add(pos, groc);
    }


    public void removeGrocery(int pos){
        mGroceries.remove(pos);
    }


    public static GroceryList get(Context context){
        if(groceryList == null){
            groceryList = new GroceryList(context);
        }
        return groceryList;
    }
}
