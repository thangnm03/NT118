package com.example.do_an.Home.Categorys.Category;

public class FoodItem {
    private String foodName;
    private String imageResource;
    private int likesCount;

    private String id_food;

    public FoodItem(String id_food,String foodName, String imageResource, int likesCount) {
        this.id_food = id_food;
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

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getId_food() {
        return id_food;
    }

    public void setId_foode(String id_food) {
        this.id_food = id_food;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "idMeal='" + id_food + '\'' +
                ", likeCount=" + likesCount +
                ", strMeal='" + foodName + '\'' +
                ", strMealThumb='" + imageResource + '\'' +
                '}';
    }
}

