package com.example.do_an.Home.Info_Dish;

public class RecipeIngredient {
    private String ingredientName;
    private String quantity;

    public RecipeIngredient(String ingredientName, String quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
