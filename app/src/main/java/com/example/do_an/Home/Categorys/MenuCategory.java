package com.example.do_an.Home.Categorys;

import com.example.do_an.Home.Categorys.Category.FoodItem;

import java.util.List;

public class MenuCategory {
    private String categoryName;
    private List<FoodItem> foodItems;

    public MenuCategory(String categoryName, List<FoodItem> foodItems) {
        this.categoryName = categoryName;
        this.foodItems = foodItems;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}