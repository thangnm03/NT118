package com.example.do_an.Home.Categorys;

import com.example.do_an.Home.Categorys.Category.FoodItem;

import java.util.ArrayList;

public class MenuCategory {
    private String categoryName;
    private ArrayList<FoodItem> foodItems;

    public MenuCategory(String categoryName, ArrayList<FoodItem> foodItems) {
        this.categoryName = categoryName;
        this.foodItems = foodItems;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
