package com.example.do_an.Home;

import com.example.do_an.Home.Categorys.Category.FoodItem;
import com.google.firebase.database.DataSnapshot;

public class DataParser {

    public static FoodItem parseMeal(DataSnapshot dataSnapshot) {
        String idMeal = dataSnapshot.child("idMeal").getValue(String.class);
        int likeCount = dataSnapshot.child("like_count").getValue(Integer.class);
        String strMeal = dataSnapshot.child("strMeal").getValue(String.class);
        String strMealThumb = dataSnapshot.child("strMealThumb").getValue(String.class);

        return new FoodItem(idMeal, strMeal, strMealThumb, likeCount);
    }
}
