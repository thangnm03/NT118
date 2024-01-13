package com.example.do_an;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class fragment_meal_plan extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_meal_plan);
    }
    public void add(View view){
        Intent intent = new Intent(fragment_meal_plan.this, item_dish.class);
        startActivity(intent);
        // finish(); xóa intent hiện tại
    }
}
