package com.example.do_an.Home.Categorys.Category;

public class FoodItem {
    private String foodName;
    private int imageResource;
    private int likesCount;

    public FoodItem(String foodName, int imageResource, int likesCount) {
        this.foodName = foodName;
        this.imageResource = imageResource;
        this.likesCount = likesCount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}
