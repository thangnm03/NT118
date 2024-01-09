package com.example.do_an.Home.Info_Dish.Tips;

public class RecipeTip {
    private String name;
    private String imageUrl;
    private String tip;
    private int likes;

    // Constructor
    public RecipeTip(String name, String imageUrl, String tip, int likes) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.tip = tip;
        this.likes = likes;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
